package com.system.store

import kotlinx.serialization.Serializable

@Serializable
data class AuthInformationDTO(
    val accessToken: String? = null,
    val refreshToken: String? = null,
    val accessTokenExpireDate: String? = null,
    val refreshTokenExpireDate: String? = null
)

@Serializable
sealed class GlobalAuthState {

    @Serializable
    data object Login : GlobalAuthState()

    @Serializable
    data object Logout : GlobalAuthState()
}