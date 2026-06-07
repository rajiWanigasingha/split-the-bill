package com.system.loadEnv

import kotlinx.serialization.Serializable

@Serializable
data class Config(
    val resend: Resend,
    val jwt: JWT
)

@Serializable
data class Resend(val key: String)

@Serializable
data class JWT(
    val secret: String,
    val issuer: String,
    val audience: String,
    val realm: String
)