package com.system.router.registration.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.resend.Resend
import com.resend.services.emails.model.CreateEmailOptions
import com.system.loadEnv.AppConfig
import com.system.router.registration.errors.RegistrationErrors
import com.system.router.registration.repos.RegistrationRepository
import com.system.router.registration.helpers.RegistrationResult
import com.system.router.registration.dtos.repoDTO.RegistrationTableDTO
import com.system.router.registration.dtos.requestDTO.RegistrationNewUserRequestDTO
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.Date

class RegistrationServiceImpl(
    private val appConfig: AppConfig,
    private val responseRepository: RegistrationRepository
) : RegistrationService {

    override fun createNewUser(registrationNewUserDTO: RegistrationNewUserRequestDTO): RegistrationResult<Unit> {
        val otp = generateOTP()

        val response = responseRepository.createUser(
            RegistrationTableDTO(
                firstName = registrationNewUserDTO.firstName,
                lastName = registrationNewUserDTO.lastName,
                userName = registrationNewUserDTO.userName,
                phoneNumber = registrationNewUserDTO.phoneNumber,
                emailAddress = registrationNewUserDTO.emailAddress,
                otp = otp.first
            )
        )

        if (response is RegistrationResult.Success) {
            val send = sendOTP(
                registrationNewUserDTO.userName,
                registrationNewUserDTO.emailAddress,
                otp.second
            )

            if (!send) {
                return RegistrationResult.Error(
                    data = RegistrationErrors.FailToSendOTP,
                    message = "Fail to send otp, try again"
                )
            }
        }

        return response
    }

    override fun generateOTP(): Pair<String, String> {
        val random = SecureRandom()
        val digest = MessageDigest.getInstance("SHA-256")

        val otp = (1..6)
            .map { random.nextInt(10) }
            .joinToString("")

        val hashOTP = digest.digest(otp.toByteArray())
            .joinToString("") { "%02x".format(it) }

        return Pair(hashOTP, otp)
    }

    override fun sendOTP(userName: String, email: String, otp: String): Boolean {
        val resendKey = appConfig.resendApiKey
        var otpTemplate = appConfig.htmlTemplate

        val resend = Resend(resendKey)

        otpTemplate = otpTemplate
            .replace("{{firstName}}", userName)
            .replace("{{otpCode}}", otp)
            .replace("{{emailAddress}}", email)

        val params = CreateEmailOptions.builder()
            .from("split-the-bill <split-the-bill@splitthebill.rajindawanigasingha.com>")
            .to(email)
            .subject("Split The Bill Registration OTP")
            .html(otpTemplate)
            .build()

        runCatching {
            resend.emails().send(params)
            return true
        }.getOrElse {
            println(it)
            return false
        }

    }

    override fun validateOTP(email: String, otp: String) {

        val digest = MessageDigest.getInstance("SHA-256")

        val hashOTP = digest.digest(otp.toByteArray())
            .joinToString("") { "%02x".format(it) }

        val validate = responseRepository.validateOTP(email ,hashOTP)

        if (validate) {
            val refreshToken = JWT.create()
                .withAudience(appConfig.jwt.audience)
                .withIssuer(appConfig.jwt.issuer)
                .withClaim("email" ,email)
                .withClaim("type", "refresh")
                .withExpiresAt(Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1 hour
                .sign(Algorithm.HMAC256(appConfig.jwt.secret))

            val accessToken = JWT.create()
                .withAudience(appConfig.jwt.audience)
                .withIssuer(appConfig.jwt.issuer)
                .withClaim("email" ,email)
                .withClaim("type", "access")
                .withExpiresAt(Date(System.currentTimeMillis() +  30L * 24 * 60 * 60 * 1000)) // 30 days
                .sign(Algorithm.HMAC256(appConfig.jwt.secret))

            val hashRefreshToken = digest.digest(refreshToken.toByteArray())
                .joinToString("") { "%02x".format(it) }

        }
    }
}