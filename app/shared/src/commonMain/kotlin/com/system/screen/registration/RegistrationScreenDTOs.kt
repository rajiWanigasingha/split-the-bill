package com.system.screen.registration

import kotlinx.serialization.Serializable

@Serializable
data class BasicInformationRegistrationDTO(
    val firstName: String,
    val lastName: String,
    val userName: String
)

@Serializable
data class BasicInformationRegistrationValidationErrorDTO(
    val firstName: String?,
    val lastName: String?,
    val userName: String?
)