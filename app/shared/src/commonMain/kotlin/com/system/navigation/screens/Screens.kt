package com.system.navigation.screens

import androidx.navigation3.runtime.NavKey
import com.system.navigation.Pages
import kotlinx.serialization.Serializable

@Serializable
sealed class Screens : NavKey {
    @Serializable data object Home : Screens()

    @Serializable data object Splits : Screens()

    @Serializable data object SplitId : Screens()


    @Serializable data object Remind : Screens()

    @Serializable data object Groups : Screens()

    @Serializable data object Registration : Screens()
}
