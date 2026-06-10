package com.system.store

import kotlinx.serialization.Serializable

@Serializable
data class AuthInformationDTO(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val accessTokenExpireDate: String? = null,
    val refreshTokenExpireDate: String? = null
)