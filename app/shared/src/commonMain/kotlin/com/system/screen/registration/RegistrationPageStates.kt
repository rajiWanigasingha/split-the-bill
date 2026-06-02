package com.system.screen.registration

sealed class BasicInformationPage {
    object Init: BasicInformationPage()
    data class CollectedBasicInformation(val data: BasicInformationRegistrationDTO): BasicInformationPage()
    data class CollectedValidationError(val data: BasicInformationRegistrationValidationErrorDTO): BasicInformationPage()
}