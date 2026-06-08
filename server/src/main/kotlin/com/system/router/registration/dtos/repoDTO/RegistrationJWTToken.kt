package com.system.router.registration.dtos.repoDTO

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationJWTToken(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpireDate: String,
    val refreshTokenExpireDate: String
)