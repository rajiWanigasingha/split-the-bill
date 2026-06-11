package com.system.screen.login.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.system.screen.registration.components.OTPInputFieldComponent
import com.system.theme.jetBrainsMonoFontFamily

@Composable
fun OTPPage() {

    var otpValue by remember { mutableStateOf("") }

    LazyColumn {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "LOG BACK IN",
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = jetBrainsMonoFontFamily(),
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Login to your account again",
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(24.dp))

                OTPInputFieldComponent(
                    otpValue = otpValue,
                    onOtpChange = {
                        otpValue = it
                    },
                    onOtpClear = {
                        otpValue = ""
                    }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    },
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Spacer(Modifier.size(8.dp))
                    Text("Send OTP")
                }
            }
        }
    }
}