package com.system.router.registration

import com.resend.Resend
import com.resend.services.emails.model.CreateEmailOptions
import com.system.LoadEnv
import java.security.MessageDigest
import java.security.SecureRandom

class RegistrationServiceImpl(
    private val responseRepository: RegistrationRepository
) : RegistrationService {

    override fun createNewUser(registrationNewUserDTO: RegistrationNewUserDTO): RegistrationResult<Unit> {
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
        val resendKey = LoadEnv.getResendKey()
        var otpTemplate = LoadEnv.getOTPTemplate()

        if (resendKey != null && otpTemplate != null) {

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
                val data = resend.emails().send(params)
                println(data.id)
                return true
            }.getOrElse {
                println(it)
                return false
            }

        } else {
            return false
        }
    }

    override fun validateOTP(email: String, otp: String) {
        val validate = responseRepository.validateOTP(email ,otp)
    }
}