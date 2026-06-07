package com.system.router.registration.repos

import com.system.router.registration.helpers.RegistrationResult
import com.system.router.registration.dtos.repoDTO.RegistrationTableDTO

interface RegistrationRepository {

    fun createUser(registrationTableDTO: RegistrationTableDTO) : RegistrationResult<Unit>

    fun validateOTP(email: String, otp: String) : Boolean

    fun storeAccessToken(email: String, accessToken: String)

}