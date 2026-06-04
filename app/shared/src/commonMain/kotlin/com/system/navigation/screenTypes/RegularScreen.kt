package com.system.navigation.screenTypes

import androidx.compose.runtime.Composable
import com.system.navigation.Screen

abstract class RegularScreen : Screen {
    @Composable
    abstract fun NavigationBuilder(
        nav: () -> Unit
    )
}