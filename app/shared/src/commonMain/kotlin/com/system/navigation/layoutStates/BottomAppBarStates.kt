package com.system.navigation.layoutStates

import androidx.lifecycle.ViewModel
import androidx.navigation3.runtime.NavKey
import com.system.navigation.screens.Screens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class BottomAppBarStates : ViewModel() {

    val bottomAppBarState: StateFlow<Screens>
        field = MutableStateFlow<Screens>(Screens.Home)

    fun navigateScreen(screen: Screens) {
        bottomAppBarState.update { screen }
    }

}