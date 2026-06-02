package com.system.screen.registration.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.close
import split_the_bill.app.shared.generated.resources.lastname

@Composable
fun LastNameComponent(
    lastName: String,
    onLastNameChange: (String) -> Unit,
    onLastNameClear: () -> Unit,
    errorMessage: String? = null
) {
    var isFocused by remember { mutableStateOf(false) }
    OutlinedTextField(
        value = lastName,
        onValueChange = { onLastNameChange(it) },
        isError = errorMessage != null,
        label = { Text("Last Name") },
        supportingText = {
            if (errorMessage == null) Text("Enter your last name") else Text(
                errorMessage
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.lastname),
                contentDescription = "lastname",
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (isFocused && lastName.isNotEmpty()) {
                IconButton(
                    onClick = { onLastNameClear() }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.close),
                        contentDescription = "close",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            },
        singleLine = true
    )
}