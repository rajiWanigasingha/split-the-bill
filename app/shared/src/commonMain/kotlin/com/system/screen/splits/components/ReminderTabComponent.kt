package com.system.screen.splits.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.reminder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReminderTabComponent() {
    val datePickerState = rememberDatePickerState()
    val timePickerState = rememberTimePickerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.surface
            )
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DatePicker(
            state = datePickerState,
            showModeToggle = false,
            headline = {
                Text(
                    text = "Reminder Date",
                    modifier = Modifier.padding(
                        horizontal = 24.dp,
                        vertical = 8.dp
                    )
                )
            }
        )
        Spacer(modifier = Modifier.height(24.dp))
        TimePicker(
            state = timePickerState
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
        ) {
            Text("Set The Reminder")
            Spacer(Modifier.size(8.dp))
            Icon(
                painter = painterResource(Res.drawable.reminder),
                contentDescription = "key",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}