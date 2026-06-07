package com.system.router.registration.repos

import com.system.router.registration.errors.RegistrationErrors
import com.system.router.registration.helpers.RegistrationResult
import com.system.router.registration.dtos.repoDTO.RegistrationTableDTO
import java.time.LocalDateTime

class RegistrationRepositoryTestImpl : RegistrationRepository {

    private val tempDB = mutableListOf<RegistrationTableDTO>()

    override fun createUser(registrationTableDTO: RegistrationTableDTO): RegistrationResult<Unit> {

        val findUser = tempDB.find { it.phoneNumber == registrationTableDTO.phoneNumber && it.emailAddress == registrationTableDTO.emailAddress }

        if (findUser != null) {
            return RegistrationResult.Error(
                data = RegistrationErrors.UserAlreadyExist
            )
        }

        val newUser = RegistrationTableDTO(
            firstName = registrationTableDTO.firstName,
            lastName = registrationTableDTO.lastName,
            userName = registrationTableDTO.userName,
            phoneNumber = registrationTableDTO.phoneNumber,
            emailAddress = registrationTableDTO.emailAddress,
            otp = registrationTableDTO.otp,
            expired = false,
            otpCreateAt = LocalDateTime.now().plusMinutes(5),
            createdAt = LocalDateTime.now()
        )

        tempDB.add(newUser)

        tempDB.forEach { println(it.toString()) }

        return RegistrationResult.Success(Unit)
    }

    override fun validateOTP(email: String, otp: String): Boolean {
        val registration = tempDB.indexOfFirst { it.emailAddress == email }

        if (registration != -1) {
            if (tempDB[registration].otp == otp) {
                tempDB[registration].expired = true
                return true
            }
        }

        return false
    }

    override fun storeAccessToken(email: String, accessToken: String) {
        TODO("Not yet implemented")
    }
}