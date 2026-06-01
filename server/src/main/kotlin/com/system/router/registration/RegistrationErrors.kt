package com.system.router.registration

import kotlinx.serialization.Serializable

sealed class RegistrationErrors {
    data object UserAlreadyExist : RegistrationErrors()
    data object FailToSendOTP : RegistrationErrors()
}

@Serializable
data class RegistrationError(
    val errorCode: Int,
    val errorMessage: String
)