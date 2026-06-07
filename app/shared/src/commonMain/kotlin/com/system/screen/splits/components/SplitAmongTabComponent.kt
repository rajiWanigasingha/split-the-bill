package com.system.screen.splits.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.backward
import split_the_bill.app.shared.generated.resources.profile

@Composable
fun SplitAmongTabComponent() {
    val contacts = listOf("Alice", "Bob", "Charlie", "David", "Eve")

    Column(modifier = Modifier.fillMaxSize().padding(vertical = 32.dp, horizontal = 16.dp).verticalScroll(
        rememberScrollState()
    )) {
        contacts.forEach {
            ListItem(
                headlineContent = { Text(it) },
                supportingContent = { Text("Split 120 in this split") },
                leadingContent = {
                    Image(
                        painter = painterResource(Res.drawable.profile),
                        contentDescription = "Profile",
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .wrapContentHeight(),
                        contentScale = ContentScale.Crop
                    )
                },
                trailingContent = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.backward),
                            contentDescription = "goto",
                            modifier = Modifier.size(20.dp).rotate(180f)
                        )
                    }
                }
            )
            HorizontalDivider()
        }
    }
}