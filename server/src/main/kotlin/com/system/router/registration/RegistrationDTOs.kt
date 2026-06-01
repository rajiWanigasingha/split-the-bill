package com.system.router.registration

import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class RegistrationNewUserDTO(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val phoneNumber: String,
    val emailAddress: String
)

data class RegistrationTableDTO(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val phoneNumber: String,
    val emailAddress: String,
    val otp: String? = null,
    val expired: Boolean = false,
    val otpCreateAt: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)