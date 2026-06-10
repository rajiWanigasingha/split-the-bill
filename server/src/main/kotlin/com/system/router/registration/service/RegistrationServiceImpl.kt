package com.system.router.registration.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.resend.Resend
import com.resend.services.emails.model.CreateEmailOptions
import com.system.loadEnv.AppConfig
import com.system.router.registration.dtos.repoDTO.RegistrationJWTToken
import com.system.router.registration.errors.RegistrationErrors
import com.system.router.registration.repos.RegistrationRepository
import com.system.router.registration.helpers.RegistrationResult
import com.system.router.registration.dtos.repoDTO.RegistrationTableDTO
import com.system.router.registration.dtos.requestDTO.RegistrationNewUserRequestDTO
import org.slf4j.LoggerFactory
import java.security.MessageDigest
import java.security.SecureRandom
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

class RegistrationServiceImpl(
    private val appConfig: AppConfig,
    private val responseRepository: RegistrationRepository
) : RegistrationService {

    private val logger = LoggerFactory.getLogger("RegistrationServiceImpl")

    override suspend fun createNewUser(registrationNewUserDTO: RegistrationNewUserRequestDTO): RegistrationResult<Unit> {
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
    override suspend fun generateOTP(): Pair<String, String> {
        val random = SecureRandom()
        val digest = MessageDigest.getInstance("SHA-256")

        val otp = (1..6)
            .map { random.nextInt(10) }
            .joinToString("")

        val hashOTP = digest.digest(otp.toByteArray())
            .joinToString("") { "%02x".format(it) }

        return Pair(hashOTP, otp)
    }
    override suspend fun sendOTP(userName: String, email: String, otp: String): Boolean {
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

    override suspend fun validateOTP(email: String, otp: String) : RegistrationJWTToken? {

        logger.info("Begin validation OTP")

        val digest = MessageDigest.getInstance("SHA-256")

        val hashOTP = digest.digest(otp.toByteArray())
            .joinToString("") { "%02x".format(it) }

        val validate = responseRepository.validateOTP(email ,hashOTP)

        if (validate) {

            logger.info("Generating JWT token")

            val refreshTokenExpireAt = Instant.now()
                .plus(30, ChronoUnit.DAYS)

            val accessTokenExpireAt = Instant.now()
                .plus(1, ChronoUnit.HOURS)

            val refreshTokenExpireAtDate = Date.from(refreshTokenExpireAt)
            val accessTokenExpireAtDate = Date.from(accessTokenExpireAt)

            val refreshToken = JWT.create()
                .withAudience(appConfig.jwt.audience)
                .withIssuer(appConfig.jwt.issuer)
                .withClaim("email" ,email)
                .withClaim("type", "refresh")
                .withExpiresAt(refreshTokenExpireAtDate)
                .sign(Algorithm.HMAC256(appConfig.jwt.secret))

            val accessToken = JWT.create()
                .withAudience(appConfig.jwt.audience)
                .withIssuer(appConfig.jwt.issuer)
                .withClaim("email" ,email)
                .withClaim("type", "access")
                .withExpiresAt(accessTokenExpireAtDate)
                .sign(Algorithm.HMAC256(appConfig.jwt.secret))

            val hashRefreshToken = digest.digest(refreshToken.toByteArray())
                .joinToString("") { "%02x".format(it) }

            logger.info("Generated jwt token")

            val refreshTokenStored = responseRepository.storeAccessToken(
                email = email,
                refreshToken = hashRefreshToken,
                refreshTokenExpireDate = refreshTokenExpireAt
            )

            return if (refreshTokenStored) {
                logger.info("Registration is successful")
                RegistrationJWTToken(
                    accessToken = accessToken,
                    refreshToken = refreshToken,
                    accessTokenExpireDate = accessTokenExpireAt.toString(),
                    refreshTokenExpireDate = refreshTokenExpireAt.toString()
                )
            } else {
                null
            }
        }

        return null
    }
}