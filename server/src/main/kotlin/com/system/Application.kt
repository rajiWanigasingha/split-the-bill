package com.system

import com.system.router.registration.RegistrationService
import com.system.router.registration.RegistrationServiceImpl
import com.system.router.registration.registrationModule
import com.system.router.registration.registrationRoute
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.routing.*
import io.ktor.server.resources.*
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger
import org.koin.plugin.module.dsl.single

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", watchPaths = listOf("classes"), module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(Resources)

    install(ContentNegotiation) {
        json()
    }

    install(Koin) {
        slf4jLogger()
        modules(
            registrationModule
        )
    }


    routing {
        registrationRoute()
    }
}
