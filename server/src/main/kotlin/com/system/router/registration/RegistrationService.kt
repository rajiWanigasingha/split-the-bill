package com.system.router.registration

interface RegistrationService {

    fun createNewUser(registrationNewUserDTO: RegistrationNewUserDTO) : RegistrationResult<Unit>

    fun generateOTP() : Pair<String , String>

    fun sendOTP(userName: String, email: String, otp: String) : Boolean

    fun validateOTP()

}