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
import split_the_bill.app.shared.generated.resources.firstname

@Composable
fun FirstNameComponent(
    firstName: String,
    onFirstNameChange: (String) -> Unit,
    onFirstNameClear: () -> Unit,
    errorMessage: String? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        isError = errorMessage != null,
        value = firstName,
        onValueChange = { onFirstNameChange(it) },
        label = { Text("First Name") },
        supportingText = {
            if (errorMessage != null) {
                Text(errorMessage)
            } else {
                Text("Enter your first name")
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.firstname),
                contentDescription = "fistName",
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (isFocused && firstName.isNotEmpty()) {
                IconButton(onClick = { onFirstNameClear() }) {
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