package com.system.screen.splits.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.system.navigation.screenTypes.RegularScreen
import com.system.screen.splits.components.InformationTabComponent
import com.system.screen.splits.components.ReminderTabComponent
import com.system.screen.splits.components.SplitAmongTabComponent
import com.system.screen.splits.states.SpiltItTabState
import com.system.screen.splits.states.SplitIdTabsViewModel

class SplitIdScreen(private val tab: SpiltItTabState) : RegularScreen() {
    @Composable
    override fun NavigationBuilder(nav: () -> Unit) {

        val splitItTabsViewModel: SplitIdTabsViewModel = viewModel { SplitIdTabsViewModel() }
        val splitItTabState = splitItTabsViewModel.tabSwitchState.collectAsState()

        LaunchedEffect(Unit) {
            splitItTabsViewModel.switchTab(tab)
        }

        when (splitItTabState.value) {
            SpiltItTabState.Information -> InformationTabComponent()
            SpiltItTabState.Reminder -> ReminderTabComponent()
            SpiltItTabState.SpiltAmong -> SplitAmongTabComponent()
        }

    }
}