package com.system.router.registration

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationNewUserDTO(
    val fistName: String,
    val lastName: String,
    val userName: String,
    val phoneNumber: String,
    val emailAddress: String
)