package com.system.screen.registration

import androidx.lifecycle.ViewModel
import com.system.screen.registration.BasicInformationPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class RegistrationScreenViewModel : ViewModel() {

    val basicInformationPageState: StateFlow<BasicInformationPage>
        field = MutableStateFlow<BasicInformationPage>(BasicInformationPage.Init)

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

}