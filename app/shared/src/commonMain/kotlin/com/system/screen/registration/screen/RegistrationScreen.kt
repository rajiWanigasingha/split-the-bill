package com.system.screen.registration.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.system.screen.registration.BasicInformationPage
import com.system.screen.registration.ContactInformationPage
import com.system.screen.registration.OneTimePasswordPage
import com.system.screen.registration.RegistrationPages
import com.system.screen.registration.RegistrationScreenViewModel

@Composable
fun RegistrationScreen(
    registrationScreenViewModel: RegistrationScreenViewModel = viewModel { RegistrationScreenViewModel() },
) {

    val registrationPagesState by registrationScreenViewModel.registrationPageState.collectAsState()

    when(registrationPagesState) {
        RegistrationPages.SetUpPage -> {
            BasicInformationPage { registrationScreenViewModel.changeRegistrationPage(RegistrationPages.ContactInformationPage) }
        }
        RegistrationPages.ContactInformationPage -> {
            ContactInformationPage(
                onBack = { registrationScreenViewModel.changeRegistrationPage(RegistrationPages.SetUpPage) }
            ) { registrationScreenViewModel.changeRegistrationPage(RegistrationPages.OTPPage) }
        }
        RegistrationPages.OTPPage -> {
            OneTimePasswordPage()
        }
    }

}