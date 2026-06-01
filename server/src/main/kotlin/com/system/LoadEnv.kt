package com.system

import io.github.cdimascio.dotenv.dotenv

object LoadEnv {

    private var resendApiKey: String? = null

    init {
        val dotenv = dotenv {
            directory = "server/src/main/resources"
            filename = ".env"
        }

        resendApiKey = dotenv["RESEND_API_KEY"]
    }

    fun getResendKey(): String? {
        return resendApiKey
    }

}