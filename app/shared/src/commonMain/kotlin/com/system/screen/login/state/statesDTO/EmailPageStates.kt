package com.system.screen.login.state.statesDTO

import kotlinx.serialization.Serializable

@Serializable
sealed class EmailPageStates {

    @Serializable
    data object Init : EmailPageStates()

    @Serializable
    data class SaveEmailState(val email: String) : EmailPageStates()

    @Serializable
    data class Error(val message: String) : EmailPageStates()

}
