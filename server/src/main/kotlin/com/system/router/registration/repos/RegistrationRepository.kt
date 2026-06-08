package com.system.router.registration.repos

import com.system.router.registration.helpers.RegistrationResult
import com.system.router.registration.dtos.repoDTO.RegistrationTableDTO
import java.time.LocalDateTime

interface RegistrationRepository {

    fun createUser(registrationTableDTO: RegistrationTableDTO) : RegistrationResult<Unit>

    fun validateOTP(email: String, otp: String) : Boolean

    fun storeAccessToken(email: String, refreshToken: String ,refreshTokenExpireDate: LocalDateTime) : Boolean

}