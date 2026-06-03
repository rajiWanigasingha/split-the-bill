package com.system.screen.registration

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