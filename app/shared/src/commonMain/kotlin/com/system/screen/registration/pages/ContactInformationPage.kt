package com.system.screen.registration.pages

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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
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
import com.system.screen.registration.state.ContactInformationPage
import com.system.screen.registration.dto.ContactInformationRegistrationDTO
import com.system.screen.registration.state.RegistrationScreenViewModel
import com.system.screen.registration.components.EmailAddressComponent
import com.system.screen.registration.components.PhoneNumberComponent
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.back
import split_the_bill.app.shared.generated.resources.key

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ContactInformationPage(
    registrationScreenViewModel: RegistrationScreenViewModel = viewModel { RegistrationScreenViewModel() },
    onBack: () -> Unit,
    onNextPage: () -> Unit
) {

    val contactInformationPageState by registrationScreenViewModel.contactInformationPageState.collectAsState()

    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf<String?>(null) }

    var phoneNumber by remember { mutableStateOf("") }
    var phoneNumberError by remember { mutableStateOf<String?>(null) }

    var selectedCountryCode by remember { mutableStateOf("LK (+94)") }
    var selectedCountryCodeError by remember { mutableStateOf<String?>(null) }

    val focusManager = LocalFocusManager.current

    when (contactInformationPageState) {
        is ContactInformationPage.Init -> {
            emailError = null
            phoneNumberError = null
        }
        is ContactInformationPage.CollectedContactInformation -> {
            registrationScreenViewModel.createNewUser()
            onNextPage()
        }
        is ContactInformationPage.CollectedValidationError -> {
            val errors = (contactInformationPageState as ContactInformationPage.CollectedValidationError).data
            emailError = errors.email
            phoneNumberError = errors.phoneNumber
        }
        is ContactInformationPage.SaveState -> {
            registrationScreenViewModel.saveContactInformation()
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
                            text = "CONTACT INFORMATION",
                            style = MaterialTheme.typography.headlineLargeEmphasized,
                            fontFamily = jetBrainsMonoFontFamily(),
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "One more step to go. This need to be a phone number and email in use.",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    EmailAddressComponent(
                        email = email,
                        onEmailChange = {
                            email = it
                        },
                        onEmailClear = {
                            email = ""
                        },
                        errorMessage = emailError
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    PhoneNumberComponent(
                        selectedCountryCode = selectedCountryCode,
                        onSelectedCountryCodeChange = { selectedCountryCode = it },
                        onSelectedCountryCodeError = selectedCountryCodeError,
                        phoneNumber = phoneNumber,
                        onPhoneNumberChange = { phoneNumber = it },
                        onPhoneNumberClear = { phoneNumber = "" },
                        onPhoneNumberError = phoneNumberError
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Button(
                            onClick = {
                                onBack()
                            },
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.back),
                                contentDescription = "Back",
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(Modifier.size(8.dp))
                            Text("Back")
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                        Button(
                            onClick = {
                                registrationScreenViewModel.setContactInformation(
                                    info = ContactInformationRegistrationDTO(
                                        email = email,
                                        phoneNumber = phoneNumber,
                                        countryCode = selectedCountryCode
                                    )
                                )
                            },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(4.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            ),
                        ) {
                            Text("Send verification OTP")
                            Spacer(Modifier.size(8.dp))
                            Icon(
                                painter = painterResource(Res.drawable.key),
                                contentDescription = "key",
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}