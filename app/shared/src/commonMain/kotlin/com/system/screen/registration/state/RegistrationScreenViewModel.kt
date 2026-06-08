package com.system.screen.registration.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.system.screen.registration.dto.BasicInformationRegistrationDTO
import com.system.screen.registration.dto.BasicInformationRegistrationValidationErrorDTO
import com.system.screen.registration.dto.ContactInformationRegistrationDTO
import com.system.screen.registration.dto.ContactInformationRegistrationValidationErrorDTO
import com.system.screen.registration.dto.RegistrationJWT
import com.system.screen.registration.dto.RegistrationNewUserDTO
import com.system.screen.registration.dto.RegistrationValidationOTP
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RegistrationScreenViewModel : ViewModel() {

    val registrationPageState: StateFlow<RegistrationPages>
        field = MutableStateFlow<RegistrationPages>(RegistrationPages.OTPPage)
    val basicInformationPageState: StateFlow<BasicInformationPage>
        field = MutableStateFlow<BasicInformationPage>(BasicInformationPage.Init)
    val contactInformationPageState: StateFlow<ContactInformationPage>
        field = MutableStateFlow<ContactInformationPage>(ContactInformationPage.Init)

    val validateOTPPageState: StateFlow<ValidateOTPPage>
        field = MutableStateFlow<ValidateOTPPage>(ValidateOTPPage.Init)

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

    fun saveBasicInformationState() {
        if (basicInformationPageState.value is BasicInformationPage.CollectedBasicInformation) {
            val info =
                (basicInformationPageState.value as BasicInformationPage.CollectedBasicInformation).data
            basicInformationPageState.update { BasicInformationPage.SaveState(info) }
        }
    }

    fun setContactInformation(info: ContactInformationRegistrationDTO) {
        contactInformationPageState.update { ContactInformationPage.Init }

        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$".toRegex()
        val isEmailValid = info.email.matches(emailRegex)

        val dialCode = info.countryCode.substringAfter("(").substringBefore(")")

        val phoneNumberWithoutZero = info.phoneNumber.removePrefix("0")

        val fullFormattedPhoneNumber = "$dialCode$phoneNumberWithoutZero"

        if (info.email.isEmpty() || phoneNumberWithoutZero.isEmpty() || !isEmailValid || info.countryCode.isEmpty()) {
            val error = ContactInformationRegistrationValidationErrorDTO(
                email = when {
                    info.email.isEmpty() -> "Email cannot be empty"
                    !isEmailValid -> "Invalid email format"
                    else -> null
                },
                phoneNumber = if (phoneNumberWithoutZero.isEmpty()) "Phone number cannot be empty" else null,
            )
            contactInformationPageState.update {
                ContactInformationPage.CollectedValidationError(
                    data = error
                )
            }
            return
        }

        val updatedInfo = info.copy(phoneNumber = fullFormattedPhoneNumber)
        contactInformationPageState.update { ContactInformationPage.CollectedContactInformation(data = updatedInfo) }
    }

    fun saveContactInformation() {
        if (contactInformationPageState.value is ContactInformationPage.CollectedContactInformation) {
            val info =
                (contactInformationPageState.value as ContactInformationPage.CollectedContactInformation).data
            contactInformationPageState.update { ContactInformationPage.SaveState(data = info) }
        }
    }

    fun createNewUser() {
        viewModelScope.launch {

            val basicInformation =
                (basicInformationPageState.value as? BasicInformationPage.SaveState)?.data
            val contactInformation =
                (contactInformationPageState.value as? ContactInformationPage.CollectedContactInformation)?.data

            if (basicInformation == null || contactInformation == null) {
                return@launch
            }

            val response = HttpClient() {
                install(ContentNegotiation) {
                    json()
                }
            }
                .post("http://localhost:8080/registration") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        RegistrationNewUserDTO(
                            firstName = basicInformation.firstName,
                            lastName = basicInformation.lastName,
                            userName = basicInformation.userName,
                            phoneNumber = contactInformation.phoneNumber,
                            emailAddress = contactInformation.email
                        )
                    )
                }

            if (response.status.value == 200) {
                saveContactInformation()
                registrationPageState.update { RegistrationPages.OTPPage }
            }
        }
    }

    fun validateOTP(otp: String) {
        viewModelScope.launch {
            validateOTPPageState.update { ValidateOTPPage.Loading }

            val contactInformation =
                (contactInformationPageState.value as? ContactInformationPage.SaveState)?.data

            if (contactInformation == null) {
                validateOTPPageState.update { ValidateOTPPage.Error("Couldn't get contact information") }
                return@launch
            }

            val response = HttpClient() {
                install(ContentNegotiation) {
                    json()
                }
            }
                .post("http://localhost:8080/registration/otp") {
                    contentType(ContentType.Application.Json)
                    setBody(
                        RegistrationValidationOTP(
                            email = contactInformation.email,
                            otp = otp
                        )
                    )
                }

            if (response.status.value == 200) {
                val jwtTokens = response.body<RegistrationJWT>()
                println(jwtTokens.refreshToken)
                println(jwtTokens.accessToken)
                println(jwtTokens.refreshTokenExpireDate)
                println(jwtTokens.accessTokenExpireDate)
            }

            validateOTPPageState.update { ValidateOTPPage.Success }
        }
    }
}