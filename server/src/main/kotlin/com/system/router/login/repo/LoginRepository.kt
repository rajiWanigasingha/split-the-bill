package com.system.router.login.repo

interface LoginRepository {
    fun checkUser(email: String)

    fun validateEmail(email: String)
}