package com.system.screen.splits.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.system.navigation.screenTypes.RegularScreen
import com.system.screen.home.RecentSplit
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.profile
import com.system.screen.splits.components.Split

class SplitScreen : RegularScreen() {
    @Composable
    override fun NavigationBuilder(nav: () -> Unit) {

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

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
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