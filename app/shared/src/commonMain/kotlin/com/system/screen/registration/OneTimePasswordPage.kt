package com.system.screen.registration

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.back
import split_the_bill.app.shared.generated.resources.register
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.system.screen.registration.components.OTPLoadingComponent
import com.system.screen.registration.components.OtpValidateComponent
import kotlinx.coroutines.launch
import split_the_bill.app.shared.generated.resources.close
import split_the_bill.app.shared.generated.resources.key


@Composable
fun OneTimePasswordPage(
    registrationScreenViewModel: RegistrationScreenViewModel = viewModel { RegistrationScreenViewModel() }
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

                    ValidateOTPPage.Success -> {}
                }
            }
        }
    }
}