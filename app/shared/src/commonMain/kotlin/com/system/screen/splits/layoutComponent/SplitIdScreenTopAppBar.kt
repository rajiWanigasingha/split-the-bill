package com.system.screen.splits.layoutComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.ui.Alignment
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.system.screen.splits.states.SpiltItTabState
import com.system.screen.splits.states.SplitIdTabsViewModel
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.backArrow
import split_the_bill.app.shared.generated.resources.bookmark
import split_the_bill.app.shared.generated.resources.profile
import split_the_bill.app.shared.generated.resources.reminder
import split_the_bill.app.shared.generated.resources.search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplitIdScreenTopAppBar(
    splitItTabsViewModel: SplitIdTabsViewModel = viewModel { SplitIdTabsViewModel() },
) {



    MediumTopAppBar(
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(Res.drawable.backArrow),
                    contentDescription = "home",
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        title = {
            val selectedTab = remember { mutableIntStateOf(0) }

            PrimaryTabRow(selectedTabIndex = selectedTab.intValue) {
                listOf("Information", "Reminders", "Split Among").forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab.intValue == index,
                        onClick = {
                            selectedTab.intValue = index
                            when (title) {
                                "Information" -> splitItTabsViewModel.switchTab(SpiltItTabState.Information)
                                "Reminders" -> splitItTabsViewModel.switchTab(SpiltItTabState.Reminder)
                                else -> splitItTabsViewModel.switchTab(SpiltItTabState.SpiltAmong)
                            }
                        },
                        text = { Text(title) },
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(Res.drawable.bookmark),
                    contentDescription = "Group",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    )

}