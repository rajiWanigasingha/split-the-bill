package com.system.router.registration

import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.registrationRoute() {
    post<Registration> {

    }
}