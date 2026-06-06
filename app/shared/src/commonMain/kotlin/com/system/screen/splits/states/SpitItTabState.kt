package com.system.screen.splits.states

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed class SpiltItTabState : NavKey {
    @Serializable object Information: SpiltItTabState()
    @Serializable object Reminder: SpiltItTabState()
    @Serializable object SpiltAmong: SpiltItTabState()
}