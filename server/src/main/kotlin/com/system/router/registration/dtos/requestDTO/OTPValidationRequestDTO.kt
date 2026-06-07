package com.system.router.registration.dtos.requestDTO

import kotlinx.serialization.Serializable

@Serializable
data class OTPValidationRequestDTO(
    val email: String,
    val otp: String
)
