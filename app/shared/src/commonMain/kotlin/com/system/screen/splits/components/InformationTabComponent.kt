package com.system.screen.splits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.close
import split_the_bill.app.shared.generated.resources.profile

@Preview
@Composable
fun InformationTabComponent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        Image(
            painter = painterResource(Res.drawable.profile),
            contentDescription = "Profile",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .wrapContentHeight(),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = "Split By Jone Doe",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Black
        )
        val tags = listOf("BreakFast" ,"Rice And Curry" ,"Roti")
        Spacer(modifier = Modifier.size(8.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(tags) { tag ->
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = tag,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Spacer(modifier = Modifier.size(24.dp))

        InfoTableComponent(
            items = listOf(
                "You Split" to "Rs. 120.00",
                "Full Price" to "Rs. 1200.00",
                "Spilt Among" to "10 People",
                "Created by" to "Jone Doe",
                "Created At" to "Today 10.30 A.M"
            )
        )

        Spacer(modifier = Modifier.size(24.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = "2026/6/7",
                        style = MaterialTheme.typography.bodyLargeEmphasized,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = jetBrainsMonoFontFamily()
                    )
                    Text(
                        text = "15:30 P.M",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = jetBrainsMonoFontFamily()
                    )
                    Text(
                        text = "Reminder Set At",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = jetBrainsMonoFontFamily()
                    )
                }
                Column {
                    var checked by remember { mutableStateOf(true) }
                    Switch(
                        checked = checked,
                        onCheckedChange = {
                            checked = it
                        }
                    )
                }
            }
        }
    }
}