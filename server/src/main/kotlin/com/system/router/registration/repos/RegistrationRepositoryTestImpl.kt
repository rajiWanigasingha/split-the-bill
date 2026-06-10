package com.system.router.registration.repos

import com.system.router.registration.errors.RegistrationErrors
import com.system.router.registration.helpers.RegistrationResult
import com.system.router.registration.dtos.repoDTO.RegistrationTableDTO
import org.slf4j.LoggerFactory
import java.time.Instant
import java.time.LocalDateTime

class RegistrationRepositoryTestImpl : RegistrationRepository {
    private val tempDB = mutableListOf<RegistrationTableDTO>()
    private val logger = LoggerFactory.getLogger("RegistrationRepositoryTestImpl")

    override suspend fun createUser(registrationTableDTO: RegistrationTableDTO): RegistrationResult<Unit> {

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

    override suspend fun validateOTP(email: String, otp: String): Boolean {

        logger.info("Looking through repository")

        val registration = tempDB.indexOfFirst { it.emailAddress == email }

        if (registration != -1) {
            logger.info("Found otp record")
            if (tempDB[registration].otp == otp) {
                logger.info("OTP is validated")
                tempDB[registration].expired = true
                return true
            }
        }

        logger.error("OTP is invalid")

        return false
    }

    override suspend fun storeAccessToken(email: String, refreshToken: String ,refreshTokenExpireDate: Instant) : Boolean {

        logger.info("Try to store access token")

        val indexOf = tempDB.indexOfFirst { it.emailAddress == email }

        if (indexOf == -1) {
            logger.error("Couldn't store jwt token")
            return false
        }

        tempDB[indexOf].refreshToken = refreshToken
        tempDB[indexOf].refreshTokenUpdateAt = LocalDateTime.now()
        tempDB[indexOf].validRefreshTokenTime = refreshTokenExpireDate

        logger.info("stored jwt token")

        return true
    }
}