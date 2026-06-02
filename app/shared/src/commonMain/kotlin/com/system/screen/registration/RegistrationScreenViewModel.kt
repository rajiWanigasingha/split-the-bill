package com.system.screen.registration

import androidx.lifecycle.ViewModel
import com.system.screen.registration.BasicInformationPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegistrationScreenViewModel : ViewModel() {

    val registrationPageState: StateFlow<RegistrationPages>
        field = MutableStateFlow<RegistrationPages>(RegistrationPages.SetUpPage)
    val basicInformationPageState: StateFlow<BasicInformationPage>
        field = MutableStateFlow<BasicInformationPage>(BasicInformationPage.Init)

    fun changeRegistrationPage(registrationPages: RegistrationPages) {
        registrationPageState.update { registrationPages }
    }

    fun setBasicInformation(info: BasicInformationRegistrationDTO) {

        basicInformationPageState.update { BasicInformationPage.Init }

        if (info.firstName.isEmpty() || info.lastName.isEmpty() || info.userName.isEmpty()) {
            val error = BasicInformationRegistrationValidationErrorDTO(
                firstName = if (info.firstName.isEmpty()) "First name cannot be empty" else null,
                lastName = if (info.lastName.isEmpty()) "Last name cannot be empty" else null,
                userName = if (info.userName.isEmpty()) "Username cannot be empty" else null
            )
            basicInformationPageState.update { BasicInformationPage.CollectedValidationError(data = error) }
            return
        }

        basicInformationPageState.update { BasicInformationPage.CollectedBasicInformation(data = info) }
    }

    fun saveState() {
        if (basicInformationPageState.value is BasicInformationPage.CollectedBasicInformation) {
            val info =
                (basicInformationPageState.value as BasicInformationPage.CollectedBasicInformation).data
            basicInformationPageState.update { BasicInformationPage.SaveState(info) }
        }
    }
}