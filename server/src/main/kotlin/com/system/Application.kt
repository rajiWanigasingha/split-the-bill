package com.system

import com.auth0.jwt.algorithms.Algorithm
import com.system.loadEnv.AppConfig
import com.system.loadEnv.Config
import com.system.loadEnv.JWT
import com.system.router.registration.di.registrationModule
import com.system.router.registration.paths.registrationRoute
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.auth.Authentication
import io.ktor.server.auth.jwt.JWTPrincipal
import io.ktor.server.auth.jwt.jwt
import io.ktor.server.config.getAs
import io.ktor.server.netty.EngineMain
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.resources.Resources
import io.ktor.server.routing.routing
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.koin.plugin.module.dsl.module

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    val apiConfigs = environment.config.getAs<Config>()
    val htmlTemplate = object {}.javaClass
        .classLoader
        .getResourceAsStream("otpTemplate.html")
        ?.bufferedReader()
        ?.readText()
        ?: error("Resource file otpTemplate.html not found")

    install(Resources)
    install(ContentNegotiation) {
        json()
    }
    install(Authentication) {
        jwt("auth-jwt") {
            realm = apiConfigs.jwt.realm
            verifier(
                com.auth0.jwt.JWT
                    .require(Algorithm.HMAC256(apiConfigs.jwt.secret))
                    .withAudience(apiConfigs.jwt.audience)
                    .withIssuer(apiConfigs.jwt.issuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.getClaim("username").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
        }
    }
    install(Koin) {
        slf4jLogger()
        modules(
            module {
                single {
                    AppConfig(
                        resendApiKey = apiConfigs.resend.key,
                        htmlTemplate = htmlTemplate,
                        jwt = JWT(
                            secret = apiConfigs.jwt.secret,
                            issuer = apiConfigs.jwt.issuer,
                            audience = apiConfigs.jwt.audience,
                            realm = apiConfigs.jwt.realm
                        )
                    )
                }
            },
            registrationModule
        )
    }


    routing {
        registrationRoute()
    }
}
