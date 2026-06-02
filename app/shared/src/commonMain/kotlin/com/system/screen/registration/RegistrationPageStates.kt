package com.system.screen.registration

sealed class BasicInformationPage {
    object Init: BasicInformationPage()
    data class CollectedBasicInformation(val data: BasicInformationRegistrationDTO): BasicInformationPage()
    data class CollectedValidationError(val data: BasicInformationRegistrationValidationErrorDTO): BasicInformationPage()
    data class SaveState(val data: BasicInformationRegistrationDTO): BasicInformationPage()
}

sealed class RegistrationPages {
    object SetUpPage : RegistrationPages()
    object ContactInformationPage : RegistrationPages()
    object OTPPage : RegistrationPages()
}