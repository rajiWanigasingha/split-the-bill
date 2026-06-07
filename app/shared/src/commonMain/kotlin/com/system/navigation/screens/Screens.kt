package com.system.navigation.screens

import androidx.navigation3.runtime.NavKey
import com.system.navigation.Pages
import com.system.screen.splits.states.SpiltItTabState
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens : NavKey {
    @Serializable data object Home : Screens()

    @Serializable data object Splits : Screens()

    @Serializable data class SplitId(val tab: SpiltItTabState = SpiltItTabState.Information) : Screens()


    @Serializable data object Remind : Screens()

    @Serializable data object Groups : Screens()

    @Serializable data object Registration : Screens()
}
