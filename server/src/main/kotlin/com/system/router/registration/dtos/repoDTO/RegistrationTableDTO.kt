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
    var refreshToken: String? = null,
    var refreshTokenUpdateAt: LocalDateTime? = null,
    var validRefreshTokenTime: LocalDateTime? = null,
    val otpCreateAt: LocalDateTime? = null,
    val createdAt: LocalDateTime = LocalDateTime.now()
)
