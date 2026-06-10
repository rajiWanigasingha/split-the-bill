package com.system.screen.registration.state

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.system.client.Client
import com.system.screen.registration.dto.BasicInformationRegistrationDTO
import com.system.screen.registration.dto.BasicInformationRegistrationValidationErrorDTO
import com.system.screen.registration.dto.ContactInformationRegistrationDTO
import com.system.screen.registration.dto.ContactInformationRegistrationValidationErrorDTO
import com.system.screen.registration.dto.RegistrationError
import com.system.screen.registration.dto.RegistrationNewUserDTO
import com.system.screen.registration.dto.RegistrationValidationOTP
import com.system.screen.registration.pages.ContactInformationPage
import com.system.store.AuthInformationDTO
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

class RegistrationScreenViewModel(
    private val client: Client
) : ViewModel() {
    val basicInformationPageState: StateFlow<BasicInformationPage>
        field = MutableStateFlow<BasicInformationPage>(BasicInformationPage.Init)
    val contactInformationPageState: StateFlow<ContactInformationPage>
        field = MutableStateFlow<ContactInformationPage>(ContactInformationPage.Init)

    val validateOTPPageState: StateFlow<ValidateOTPPage>
        field = MutableStateFlow<ValidateOTPPage>(ValidateOTPPage.Init)

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

    fun resetContactInformation() {
        contactInformationPageState.update { ContactInformationPage.Init }
    }

    fun saveContactInformation(data: ContactInformationRegistrationDTO) {
        contactInformationPageState.update { ContactInformationPage.SaveState(data = data) }
    }

    fun createNewUser() {
        viewModelScope.launch {
            val basicInformation =
                (basicInformationPageState.value as? BasicInformationPage.SaveState)?.data
            val contactInformation =
                (contactInformationPageState.value as? ContactInformationPage.CollectedContactInformation)?.data

            contactInformationPageState.update { ContactInformationPage.Loading }

            if (basicInformation == null || contactInformation == null) {
                contactInformationPageState.update {
                    ContactInformationPage.ServerError(
                        RegistrationError(
                            errorCode = 11,
                            errorMessage = "Internal Error, Couldn't find basic information"
                        )
                    )
                }
                return@launch
            }

            val response = client.post("/registration") {
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
                saveContactInformation(contactInformation)
            } else {
                runCatching {
                    val error = response.body<RegistrationError>()

                    contactInformationPageState.update { ContactInformationPage.ServerError(error) }
                }.onFailure {
                    contactInformationPageState.update {
                        ContactInformationPage.ServerError(
                            RegistrationError(
                                errorCode = 10,
                                errorMessage = "Unknow server error"
                            )
                        )
                    }
                }
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

            val response = client.post("/registration/otp") {
                contentType(ContentType.Application.Json)
                setBody(
                    RegistrationValidationOTP(
                        email = contactInformation.email,
                        otp = otp
                    )
                )
            }

            if (response.status.value == 200) {
                val jwtTokens = response.body<AuthInformationDTO>()
                validateOTPPageState.update {
                    ValidateOTPPage.Success(
                        AuthInformationDTO(
                            accessToken = jwtTokens.accessToken,
                            refreshToken = jwtTokens.refreshToken,
                            accessTokenExpireDate = jwtTokens.accessTokenExpireDate,
                            refreshTokenExpireDate = jwtTokens.refreshTokenExpireDate
                        )
                    )
                }
            } else {
                runCatching {
                    val error = response.body<RegistrationError>()

                    validateOTPPageState.update { ValidateOTPPage.Error(error.errorMessage) }
                }.onFailure {
                    validateOTPPageState.update { ValidateOTPPage.Error("Unknown Error. Please Try Again") }
                }
            }
        }
    }
}