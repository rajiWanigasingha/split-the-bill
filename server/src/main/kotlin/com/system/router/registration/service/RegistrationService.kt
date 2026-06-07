package com.system.router.registration.service

import com.system.router.registration.helpers.RegistrationResult
import com.system.router.registration.dtos.requestDTO.RegistrationNewUserRequestDTO

interface RegistrationService {

    fun createNewUser(registrationNewUserDTO: RegistrationNewUserRequestDTO) : RegistrationResult<Unit>

    fun generateOTP() : Pair<String , String>

    fun sendOTP(userName: String, email: String, otp: String) : Boolean

    fun validateOTP(email: String, otp: String)

}