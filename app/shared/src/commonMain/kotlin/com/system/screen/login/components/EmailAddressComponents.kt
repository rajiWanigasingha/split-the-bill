package com.system.screen.login.components

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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.ktor.websocket.Frame
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.close
import split_the_bill.app.shared.generated.resources.email

@Composable
fun EmailAddressComponents(
    email: String,
    onEmailChange: (String) -> Unit,
    onEmailCancel: () -> Unit
) {

    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = email,
        onValueChange = {
            onEmailChange(it)
        },
        label = { Text("Email Address") },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                isFocused = it.isFocused
            },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true,
        textStyle = TextStyle(
            textAlign = TextAlign.Center,
            letterSpacing = 8.sp,
            fontSize = 20.sp
        ),
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.email),
                contentDescription = "key",
                modifier = Modifier.size(20.dp)
            )
        },
        trailingIcon = {
            if (isFocused) {
                IconButton(
                    onClick = {
                        onEmailCancel()
                    }
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.close),
                        contentDescription = "close",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        supportingText = {
            Text("Enter your email address")
        }
    )
}