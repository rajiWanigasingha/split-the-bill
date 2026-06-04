package com.system.screen.registration.state

import com.system.screen.registration.dto.BasicInformationRegistrationDTO
import com.system.screen.registration.dto.BasicInformationRegistrationValidationErrorDTO
import com.system.screen.registration.dto.ContactInformationRegistrationDTO
import com.system.screen.registration.dto.ContactInformationRegistrationValidationErrorDTO

sealed class BasicInformationPage {
    object Init: BasicInformationPage()
    data class CollectedBasicInformation(val data: BasicInformationRegistrationDTO): BasicInformationPage()
    data class CollectedValidationError(val data: BasicInformationRegistrationValidationErrorDTO): BasicInformationPage()
    data class SaveState(val data: BasicInformationRegistrationDTO): BasicInformationPage()
}

sealed class ContactInformationPage {
    object Init: ContactInformationPage()
    data class CollectedContactInformation(val data: ContactInformationRegistrationDTO): ContactInformationPage()
    data class CollectedValidationError(val data: ContactInformationRegistrationValidationErrorDTO): ContactInformationPage()
    data class SaveState(val data: ContactInformationRegistrationDTO): ContactInformationPage()
}

sealed class RegistrationPages {
    object SetUpPage : RegistrationPages()
    object ContactInformationPage : RegistrationPages()
    object OTPPage : RegistrationPages()
}

sealed class ValidateOTPPage {
    object Init: ValidateOTPPage()
    object Success: ValidateOTPPage()
    data class Error(val error: String): ValidateOTPPage()
    object Loading: ValidateOTPPage()
}