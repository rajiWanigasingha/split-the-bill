package com.system.screen.registration

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.system.screen.registration.components.FirstNameComponent
import com.system.screen.registration.components.LastNameComponent
import com.system.screen.registration.components.UserNameComponent
import com.system.theme.jetBrainsMonoFontFamily

@Composable
fun BasicInformationPage(
    registrationScreenViewModel: RegistrationScreenViewModel = viewModel { RegistrationScreenViewModel() },
    nextPage: () -> Unit
) {

    val basicInformationState by registrationScreenViewModel.basicInformationPageState.collectAsState()

    var firstName by remember { mutableStateOf("") }
    var firstNameError by remember { mutableStateOf<String?>(null) }
    var lastName by remember { mutableStateOf("") }
    var lastNameError by remember { mutableStateOf<String?>(null) }
    var userName by remember { mutableStateOf("") }
    var userNameError by remember { mutableStateOf<String?>(null) }

    val focusManager = LocalFocusManager.current

    when (basicInformationState) {
        is BasicInformationPage.CollectedBasicInformation -> {
            firstNameError = null
            lastNameError = null
            userNameError = null
            nextPage()
            registrationScreenViewModel.saveBasicInformationState()
        }
        is BasicInformationPage.CollectedValidationError -> {
            val error = (basicInformationState as BasicInformationPage.CollectedValidationError).data
            firstNameError = error.firstName
            lastNameError = error.lastName
            userNameError = error.userName
        }
        is BasicInformationPage.Init -> {
            firstNameError = null
            lastNameError = null
            userNameError = null
        }
        is BasicInformationPage.SaveState -> {
            val info = (basicInformationState as BasicInformationPage.SaveState).data
            firstName = info.firstName
            lastName = info.lastName
            userName = info.userName
            firstNameError = null
            lastNameError = null
            userNameError = null
        }
    }

    Scaffold {
        Box(
            modifier = Modifier
                .safeContentPadding()
                .padding(16.dp)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            contentAlignment = Alignment.Center
        ) {
            LazyColumn {

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "BASIC INFORMATION",
                            style = MaterialTheme.typography.headlineLarge,
                            fontFamily = jetBrainsMonoFontFamily(),
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "Easy way to split bill among group of people.",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    FirstNameComponent(
                        firstName = firstName,
                        onFirstNameChange = {
                            firstName = it
                        },
                        onFirstNameClear = {
                            firstName = ""
                        },
                        errorMessage = firstNameError
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    LastNameComponent(
                        lastName = lastName,
                        onLastNameChange = {
                            lastName = it
                        },
                        onLastNameClear = {
                            lastName = ""
                        },
                        errorMessage = lastNameError
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    UserNameComponent(
                        userName = userName,
                        onUserNameChange = {
                            userName = it
                        },
                        onUserNameClear = {
                            userName = ""
                        },
                        errorMessage = userNameError
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Button(
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        onClick = {
                            registrationScreenViewModel.setBasicInformation(
                                info = BasicInformationRegistrationDTO(
                                    firstName = firstName,
                                    lastName = lastName,
                                    userName = userName
                                )
                            )
//                            nextPage()
                        }
                    ) {
                        Text("Begin Creating My Account")
                    }
                }
            }
        }
    }
}