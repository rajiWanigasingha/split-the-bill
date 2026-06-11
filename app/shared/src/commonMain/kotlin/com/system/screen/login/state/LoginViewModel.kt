package com.system.screen.login.state

import androidx.lifecycle.ViewModel
import com.system.client.Client
import com.system.screen.login.state.statesDTO.EmailPageStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel(
    private val client: Client
) : ViewModel() {

    val emailPageState: StateFlow<EmailPageStates>
        field = MutableStateFlow<EmailPageStates>(EmailPageStates.Init)

    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[a-z]{2,}$".toRegex()

    fun setEmailAddress(email: String) {
        if (email.isBlank()) {
            emailPageState.update { EmailPageStates.Error("Email can't be empty") }
        } else if (!email.matches(emailRegex)) {
            emailPageState.update { EmailPageStates.Error("Not a valid email format") }
        } else {
            emailPageState.update { EmailPageStates.SaveEmailState(email) }
        }
    }
}