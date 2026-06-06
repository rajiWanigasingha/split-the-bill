package com.system.screen.splits.screen

import androidx.compose.runtime.Composable
import com.system.navigation.screenTypes.RegularScreen
import com.system.screen.splits.components.InformationTabComponent

class SplitIdScreen : RegularScreen() {
    @Composable
    override fun NavigationBuilder(nav: () -> Unit) {
        InformationTabComponent()
    }
}