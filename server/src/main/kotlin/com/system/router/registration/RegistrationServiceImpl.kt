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

        val response =  responseRepository.createUser(
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
            val send = sendOTP(registrationNewUserDTO.emailAddress, otp.second)

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

        return Pair(hashOTP ,otp)
    }

    override fun sendOTP(email: String, otp: String) : Boolean {
        val resendKey = LoadEnv.getResendKey()

        if (resendKey != null) {

            val resend = Resend(resendKey)

            val params = CreateEmailOptions.builder()
                .from("Acme <split-the-bill@splitthebill.rajindawanigasingha.com>")
                .to(email)
                .subject("Registration OTP")
                .html("<p>${otp}</p>")
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

    override fun validateOTP() {
        TODO("Not yet implemented")
    }
}