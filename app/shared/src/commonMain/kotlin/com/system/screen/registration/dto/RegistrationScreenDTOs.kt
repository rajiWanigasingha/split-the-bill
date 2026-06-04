package com.system.screen.registration.dto

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

@Serializable
data class ContactInformationRegistrationDTO(
    val email: String,
    val phoneNumber: String,
    val countryCode: String
)

@Serializable
data class ContactInformationRegistrationValidationErrorDTO(
    val email: String?,
    val phoneNumber: String?
)

@Serializable
data class RegistrationNewUserDTO(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val phoneNumber: String,
    val emailAddress: String
)