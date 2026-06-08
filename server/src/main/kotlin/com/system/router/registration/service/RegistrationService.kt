package com.system.router.registration.service

import com.system.router.registration.dtos.repoDTO.RegistrationJWTToken
import com.system.router.registration.helpers.RegistrationResult
import com.system.router.registration.dtos.requestDTO.RegistrationNewUserRequestDTO

interface RegistrationService {

    suspend fun createNewUser(registrationNewUserDTO: RegistrationNewUserRequestDTO) : RegistrationResult<Unit>

    suspend fun generateOTP() : Pair<String , String>

    suspend fun sendOTP(userName: String, email: String, otp: String) : Boolean

    suspend fun validateOTP(email: String, otp: String) : RegistrationJWTToken?

}