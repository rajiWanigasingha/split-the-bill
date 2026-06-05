package com.system.screen.splits.layoutComponent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.backArrow
import split_the_bill.app.shared.generated.resources.search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SplitScreenTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior
) {
    val collapsedFraction = scrollBehavior.state.collapsedFraction

    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
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
            if (collapsedFraction == 0f) {
                Column {
                    Text(
                        text = "Splits",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = "All Split Bills",
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
            IconButton(onClick = {}) {
                Icon(
                    painter = painterResource(Res.drawable.search),
                    contentDescription = "search",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    )
}