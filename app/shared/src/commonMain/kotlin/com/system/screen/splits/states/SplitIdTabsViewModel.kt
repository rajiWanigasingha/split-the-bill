package com.system.screen.splits.states

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SplitIdTabsViewModel : ViewModel() {

    val tabSwitchState: StateFlow<SpiltItTabState>
        field = MutableStateFlow<SpiltItTabState>(SpiltItTabState.Information)

    fun switchTab(tab: SpiltItTabState) {
        tabSwitchState.update { tab }
    }

}