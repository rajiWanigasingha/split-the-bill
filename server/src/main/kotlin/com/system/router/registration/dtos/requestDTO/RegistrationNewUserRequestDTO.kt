package com.system.router.registration.dtos.requestDTO

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationNewUserRequestDTO(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val phoneNumber: String,
    val emailAddress: String
)
