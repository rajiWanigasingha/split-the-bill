package com.system.screen.registration.pages

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.system.screen.registration.state.RegistrationScreenViewModel
import com.system.screen.registration.state.ValidateOTPPage
import com.system.screen.registration.components.OTPLoadingComponent
import com.system.screen.registration.components.OtpValidateComponent
import kotlinx.coroutines.launch


@Composable
fun OneTimePasswordPage(
    registrationScreenViewModel: RegistrationScreenViewModel = viewModel { RegistrationScreenViewModel() },
    onSuccess: ()-> Unit
) {

    val focusManager = LocalFocusManager.current
    val otpValidationState by registrationScreenViewModel.validateOTPPageState.collectAsState()

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
            val snackBarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            AnimatedContent(
                targetState = otpValidationState,
                transitionSpec = {
                    fadeIn(tween(300)) togetherWith fadeOut(tween(300))
                }
            ) { state ->
                when (state) {
                    ValidateOTPPage.Init, is ValidateOTPPage.Error -> {
                        OtpValidateComponent(
                            onCompleteOTPValue = {
                                registrationScreenViewModel.validateOTP(it)
                            }
                        )
                        SnackbarHost(
                            hostState = snackBarHostState
                        )

                        if (state is ValidateOTPPage.Error) {
                            scope.launch {
                                snackBarHostState.showSnackbar(state.error)
                            }
                        }
                    }

                    ValidateOTPPage.Loading -> {
                        OTPLoadingComponent()
                    }

                    ValidateOTPPage.Success -> {
                        onSuccess()
                    }
                }
            }
        }
    }
}