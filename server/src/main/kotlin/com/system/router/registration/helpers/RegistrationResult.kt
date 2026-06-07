package com.system.router.registration.helpers

import com.system.router.registration.errors.RegistrationErrors

sealed class RegistrationResult<out T> {
    data class Success<T>(val data: T) : RegistrationResult<T>()
    data class Error(val data: RegistrationErrors, val message: String? = null) : RegistrationResult<Unit>()
}