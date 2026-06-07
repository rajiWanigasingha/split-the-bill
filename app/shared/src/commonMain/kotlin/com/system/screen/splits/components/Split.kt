package com.system.screen.splits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalFloatingToolbar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.bookmark
import split_the_bill.app.shared.generated.resources.expand
import split_the_bill.app.shared.generated.resources.group
import split_the_bill.app.shared.generated.resources.reminder

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun Split(
    icon: DrawableResource,
    runBy: String,
    mySplit: Float,
    splitAmong: Int,
    dateTime: String,
    tags: List<String>,
    actionExpand: () -> Unit = {},
    reminderTab: () -> Unit = {},
    splitAmongTab: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(icon),
                            contentDescription = "Profile",
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(50.dp)
                                .clip(CircleShape)
                                .wrapContentHeight(),
                            contentScale = ContentScale.Crop
                        )
                        Column {
                            Text(
                                text = "This Run By",
                                style = MaterialTheme.typography.labelSmall
                            )
                            Text(
                                text = runBy,
                                style = MaterialTheme.typography.bodyLargeEmphasized.copy(
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    }
                }
                Column {
                    Text(
                        text = "Rs. ${mySplit.toInt()}.${
                            ((mySplit % 1) * 100).toInt().toString()
                                .padEnd(2, '0')
                        }",
                        style = MaterialTheme.typography.titleLargeEmphasized.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = jetBrainsMonoFontFamily()
                        )
                    )
                }
            }
            Spacer(modifier = Modifier.size(8.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                SuggestionChip(
                    onClick = {},
                    label = {
                        Text(
                            "Split Among $splitAmong",
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                )
                SuggestionChip(
                    onClick = {},
                    label = {
                        Text(
                            dateTime,
                            style = MaterialTheme.typography.labelSmall
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Row {
                if (tags.isNotEmpty()) {
                    Text(
                        text = "This Expense is created by $runBy at ${dateTime}, for ${
                            tags.joinToString(
                                ", "
                            )
                        } and this expense split among $splitAmong more others.",
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                HorizontalFloatingToolbar(
                    expanded = true,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                actionExpand()
                            },
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.expand),
                                contentDescription = "Group",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    },
                    content = {
                        IconButton(
                            onClick = {
                                splitAmongTab()
                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.group),
                                contentDescription = "Group",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        IconButton(
                            onClick = {}
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.bookmark),
                                contentDescription = "Group",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        IconButton(
                            onClick = {
                                reminderTab()
                            }
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.reminder),
                                contentDescription = "Group",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}