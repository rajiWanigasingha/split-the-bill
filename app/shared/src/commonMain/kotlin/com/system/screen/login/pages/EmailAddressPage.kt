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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.system.screen.login.components.EmailAddressComponents
import com.system.screen.login.state.LoginViewModel
import com.system.screen.login.state.statesDTO.EmailPageStates
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.back
import split_the_bill.app.shared.generated.resources.backward
import kotlin.math.log

@Composable
fun EmailAddressPage(
    loginViewModel: LoginViewModel = koinViewModel(),
    sendOTP: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    val emailPageState = loginViewModel.emailPageState.collectAsState()

    when (emailPageState.value) {
        EmailPageStates.Init -> {}
        is EmailPageStates.Error -> {}
        is EmailPageStates.SaveEmailState -> {
            sendOTP()
        }
    }

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

                EmailAddressComponents(
                    email = email,
                    onEmailChange = {
                        email = it
                    },
                    onEmailCancel = {
                        email = ""
                    },
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
                        loginViewModel.setEmailAddress(email)
                    },
                    shape = RoundedCornerShape(4.dp),
                ) {
                    Text("Send OTP")
                    Spacer(Modifier.size(8.dp))
                    Icon(
                        painter = painterResource(Res.drawable.backward),
                        contentDescription = "close",
                        modifier = Modifier.size(20.dp).rotate(180f)
                    )
                }
            }
        }
    }
}