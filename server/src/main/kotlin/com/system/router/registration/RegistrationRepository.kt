package com.system.router.registration

interface RegistrationRepository {

    fun createUser(registrationTableDTO: RegistrationTableDTO) : RegistrationResult<Unit>

    fun validateOTP()

}