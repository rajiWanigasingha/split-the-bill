package com.system

import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.system.screen.registration.BasicInformationPage
import com.system.screen.registration.OneTimePasswordPage
import com.system.screen.registration.ContactInformationPage
import com.system.theme.appTypography
import com.system.theme.splitItColorSchema


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview
fun App() {
    MaterialExpressiveTheme(
        colorScheme = splitItColorSchema,
        typography = appTypography(),
        motionScheme = MotionScheme.expressive()
    ) {
//        HomePage()

        var page by remember { mutableStateOf("") }

        when (page) {
            "" -> {
                BasicInformationPage {
                    page = "phone"
                }
            }

            "phone" -> {
                ContactInformationPage(
                    onBack = {
                        page = ""
                    }
                ) {
                    page = "otp"
                }
            }

            "otp" -> {
                OneTimePasswordPage {
                    page = "phone"
                }
            }
        }
    }
}
