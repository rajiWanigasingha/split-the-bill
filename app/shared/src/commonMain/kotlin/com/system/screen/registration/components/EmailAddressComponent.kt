package com.system.screen.registration.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.close
import split_the_bill.app.shared.generated.resources.email

@Composable
fun EmailAddressComponent(
    email: String,
    onEmailChange: (String) -> Unit,
    onEmailClear: () -> Unit,
    errorMessage: String? = null
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        isError = errorMessage != null,
        label = { Text("Email Address") },
        supportingText = {
            if (errorMessage == null) {
                Text("Enter your email address")
            } else {
                Text(errorMessage)
            }
        },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.email),
                contentDescription = "email",
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (isFocused && email.isNotEmpty()) {
                IconButton(
                    onClick = { onEmailClear() }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.close),
                        contentDescription = "close",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            },
        singleLine = true
    )
}