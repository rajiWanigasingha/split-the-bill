package com.system.router.registration.dtos.repoDTO

import java.time.LocalDateTime

data class RegistrationTableDTO(
    val firstName: String,
    val lastName: String,
    val userName: String,
    val phoneNumber: String,
    val emailAddress: String,
    val otp: String? = null,
    var expired: Boolean = false,
    val otpCreateAt: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
