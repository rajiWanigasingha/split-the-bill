package com.system.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.system.screen.splits.components.Split
import com.system.store.GlobalStoreViewModel
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.add
import split_the_bill.app.shared.generated.resources.all_splits
import split_the_bill.app.shared.generated.resources.bookmark
import split_the_bill.app.shared.generated.resources.expand
import split_the_bill.app.shared.generated.resources.group
import split_the_bill.app.shared.generated.resources.home
import split_the_bill.app.shared.generated.resources.menu
import split_the_bill.app.shared.generated.resources.profile
import split_the_bill.app.shared.generated.resources.reminder

data class RecentSplit(
    val icon: DrawableResource,
    val runBy: String,
    val mySplit: Float,
    val splitAmong: Int,
    val tags: List<String>,
    val dateTime: String
)

val recentSplitsExamples = listOf(
    RecentSplit(
        Res.drawable.profile,
        "Jane Doe",
        45.50f,
        3,
        listOf("Dinner", "Food"),
        "Today, 8:30 PM"
    ),
    RecentSplit(
        Res.drawable.profile,
        "John Smith",
        12.00f,
        2,
        listOf("Coffee"),
        "Yesterday, 10:15 AM"
    ),
    RecentSplit(
        Res.drawable.profile,
        "Alice Wong",
        120.00f,
        4,
        listOf("Groceries", "Weekly"),
        "Oct 24, 6:00 PM"
    ),
    RecentSplit(
        Res.drawable.profile,
        "Bob Miller",
        15.00f,
        5,
        listOf("Uber", "Transport"),
        "Oct 22, 11:30 PM"
    ),
    RecentSplit(
        Res.drawable.profile,
        "Charlie Brown",
        8.50f,
        2,
        listOf("Snacks"),
        "Oct 20, 4:20 PM"
    )
)


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomePage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .verticalScroll(rememberScrollState())
    ) {
        Column {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                ) {
                    Text(
                        text = "Rs.",
                        style = MaterialTheme.typography.headlineSmall
                    )
                    BasicText(
                        text = "120.00",
                        maxLines = 1,
                        style = MaterialTheme.typography.headlineLargeEmphasized,
                        autoSize = TextAutoSize.StepBased(
                            minFontSize = 10.sp,
                            maxFontSize = 60.sp
                        )
                    )
                    Text(
                        text = "In Total To PayUp",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Most Recent Splits",
                    style = MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "Most recent Spending that split",
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }

            Column {
                recentSplitsExamples.forEach {
                    Split(
                        icon = it.icon,
                        runBy = it.runBy,
                        mySplit = it.mySplit,
                        splitAmong = it.splitAmong,
                        dateTime = it.dateTime,
                        tags = it.tags
                    )
                }
            }
        }
    }
}