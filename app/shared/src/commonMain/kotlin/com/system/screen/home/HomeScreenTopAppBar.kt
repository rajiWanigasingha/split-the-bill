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
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
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

@Composable
fun HomeScreenTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    val collapsedFraction = scrollBehavior.state.collapsedFraction

    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(Res.drawable.menu),
                    contentDescription = "home",
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        title = {
            if (collapsedFraction == 0f) {
                Column {
                    Text(
                        text = "Welcome back !",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "Rajinda Wanigasingha",
                        style = MaterialTheme.typography.titleLargeEmphasized,
                        fontFamily = jetBrainsMonoFontFamily(),
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                Text(
                    text = "SPLIT-IT",
                    style = MaterialTheme.typography.titleLargeEmphasized,
                    fontFamily = jetBrainsMonoFontFamily(),
                    fontWeight = FontWeight.Black
                )
            }
        },
        actions = {
            Image(
                painter = painterResource(Res.drawable.profile),
                contentDescription = "Profile",
                modifier = Modifier
                    .padding(4.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .wrapContentHeight()
                    .clickable {},
                contentScale = ContentScale.Crop
            )
        }
    )
}