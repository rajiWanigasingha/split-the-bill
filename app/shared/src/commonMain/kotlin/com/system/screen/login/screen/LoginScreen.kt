package com.system.screen.login.screen

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.system.navigation.Pages
import com.system.navigation.screenTypes.RegularScreen
import com.system.screen.login.components.EmailAddressComponents
import com.system.screen.login.pages.EmailAddressPage
import com.system.screen.login.pages.OTPPage
import com.system.screen.registration.components.OTPInputFieldComponent
import com.system.store.GlobalStoreViewModel
import com.system.theme.jetBrainsMonoFontFamily
import org.koin.compose.viewmodel.koinViewModel
import kotlinx.serialization.Serializable

class LoginScreen : RegularScreen() {

    sealed class ScreenPages {
        @Serializable
        data object EmailPage : Pages()

        @Serializable
        object OPTPage : Pages()
    }

    @Composable
    override fun NavigationBuilder(nav: () -> Unit) {

        val globalStoreViewModel = koinViewModel<GlobalStoreViewModel>()

        val focusManager = LocalFocusManager.current
        val backStack: MutableList<Pages> = rememberSerializable(
            serializer = SnapshotStateListSerializer()
        ) {
            mutableStateListOf(ScreenPages.EmailPage)
        }

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
                    if (backStack.last() !is ScreenPages.OPTPage) {
                        backStack.removeLastOrNull()
                    }
                },
                entryProvider = entryProvider {
                    entry<ScreenPages.EmailPage> {
                        EmailAddressPage()
                    }

                    entry<ScreenPages.OPTPage> {
                        OTPPage()
                    }
                }
            )
        }
    }
}