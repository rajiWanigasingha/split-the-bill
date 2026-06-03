package com.system.screen.registration.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.close
import split_the_bill.app.shared.generated.resources.key
import split_the_bill.app.shared.generated.resources.register

@Composable
fun OtpValidateComponent(
    onCompleteOTPValue: (String) -> Unit
) {

    var otpValue by remember { mutableStateOf("") }

    LaunchedEffect(otpValue) {
        if (otpValue.length == 6) {
            onCompleteOTPValue(otpValue)
        }
    }

    LazyColumn {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ONE TIME PASSWORD",
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = jetBrainsMonoFontFamily(),
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Enter the OTP that you will receive to the email address.",
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
                OutlinedButton(
                    onClick = {
                    },
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Spacer(Modifier.size(8.dp))
                    Text("Resend OTP")
                }
                Spacer(modifier = Modifier.padding(4.dp))
                Button(
                    onClick = {
                        onCompleteOTPValue(otpValue)
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(4.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                ) {
                    Text("Setup My Account")
                    Spacer(Modifier.size(8.dp))
                    Icon(
                        painter = painterResource(Res.drawable.register),
                        contentDescription = "Back",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}