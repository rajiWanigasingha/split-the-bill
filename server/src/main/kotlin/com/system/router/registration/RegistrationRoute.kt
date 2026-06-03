package com.system.router.registration

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject
import org.slf4j.LoggerFactory

fun Route.registrationRoute() {

    val registrationService by inject<RegistrationService>()
    val logger = LoggerFactory.getLogger("/registration")

    post("/registration") {

        val userRegisterObj = runCatching {
            call.receive<RegistrationNewUserDTO>()
        }.getOrElse {
            logger.error(it.message, it)
            call.respondText("Fail")
            return@post
        }

        val registration = registrationService.createNewUser(userRegisterObj)

        if (registration is RegistrationResult.Error) {
            when (registration.data) {
                RegistrationErrors.UserAlreadyExist -> {
                    call.respond(
                        HttpStatusCode.Conflict,
                        RegistrationError(
                            errorCode = 2,
                            errorMessage = "User already exist. Use different ant phone number or email."
                        )
                    )
                }

                RegistrationErrors.FailToSendOTP -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        RegistrationError(
                            errorCode = 3,
                            errorMessage = "Fail to send OTP. Please click button that say to send otp again"
                        )
                    )
                }

                else -> {
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        RegistrationError(
                            errorCode = 1,
                            errorMessage = "Unknown Error"
                        )
                    )
                }
            }
        } else {
            call.respond(Unit)
        }
    }

    post("/registration/otp") {
        val otpValidationObject =  runCatching {
            call.receive<OTPValidationDTO>()
        }.getOrElse {
            logger.error(it.message, it)
            call.respondText("Fail")
            return@post
        }


    }
}