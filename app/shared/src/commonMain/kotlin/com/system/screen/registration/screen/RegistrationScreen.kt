package com.system.screen.registration.screen

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.system.navigation.Pages
import com.system.navigation.screenTypes.RegularScreen
import com.system.screen.registration.pages.BasicInformationPage
import com.system.screen.registration.pages.ContactInformationPage
import com.system.screen.registration.pages.OneTimePasswordPage
import kotlinx.serialization.Serializable

class RegistrationScreen(
    private val snackBarHostState : SnackbarHostState
) : RegularScreen() {

    sealed class ScreenPages {
        @Serializable
        data object BasicInformationPage : Pages()

        @Serializable
        object ContactInformationPage : Pages()

        @Serializable
        object OneTimePasswordPage : Pages()
    }

    @Composable
    override fun NavigationBuilder(
        nav: () -> Unit
    ) {
        val backStack: MutableList<Pages> = rememberSerializable(
            serializer = SnapshotStateListSerializer()
        ) {
            mutableStateListOf(ScreenPages.BasicInformationPage)
        }
        NavDisplay(
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(300)
                )
            },
            popTransitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(300)
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )
            },
            predictivePopTransitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(300)
                ) togetherWith slideOutHorizontally(
                    targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(300)
                )
            },
            backStack = backStack,
            onBack = {
                if (backStack.last() !is ScreenPages.OneTimePasswordPage) {
                    backStack.removeLastOrNull()
                }
            },
            entryProvider = entryProvider {
                entry<ScreenPages.BasicInformationPage> {
                    BasicInformationPage(
                        nextPage = {
                            backStack.add(ScreenPages.ContactInformationPage)
                        }
                    )
                }
                entry<ScreenPages.ContactInformationPage> {
                    ContactInformationPage(
                        snackBarHostState = snackBarHostState,
                        onBack = {
                            backStack.removeLastOrNull()
                        },
                        onNextPage = {
                            backStack.add(ScreenPages.OneTimePasswordPage)
                        }
                    )
                }
                entry<ScreenPages.OneTimePasswordPage> {
                    OneTimePasswordPage {
                        nav()
                    }
                }
            }
        )
    }
}

