package com.system

import io.github.cdimascio.dotenv.dotenv

object LoadEnv {

    private var resendApiKey: String? = null
    private var otpTemplate: String? = null

    init {
        val dotenv = dotenv {
            directory = "server/src/main/resources"
            filename = ".env"
        }

        resendApiKey = dotenv["RESEND_API_KEY"]

        otpTemplate = object {}.javaClass
            .classLoader
            .getResourceAsStream("otpTemplate.html")
            ?.bufferedReader()
            ?.readText()
    }

    fun getResendKey(): String? {
        return resendApiKey
    }

    fun getOTPTemplate(): String? {
        return otpTemplate
    }
}