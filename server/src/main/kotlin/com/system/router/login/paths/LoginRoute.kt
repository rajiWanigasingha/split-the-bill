package com.system.router.login.paths

import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.slf4j.LoggerFactory

fun Route.loginRoute() {

    val logger = LoggerFactory.getLogger("/login")

    post("/login") {
        call.respondText("Hello")
    }

}