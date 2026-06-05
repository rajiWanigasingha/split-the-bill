package com.system

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MotionScheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.system.navigation.screens.Screens
import com.system.screen.groups.layoutComponent.GroupScreenTopAppBar
import com.system.screen.groups.screen.GroupScreen
import com.system.screen.home.HomePage
import com.system.screen.home.HomeScreenTopAppBar
import com.system.screen.registration.screen.RegistrationScreen
import com.system.screen.splits.layoutComponent.SplitScreenTopAppBar
import com.system.screen.splits.screen.SplitScreen
import com.system.theme.appTypography
import com.system.theme.splitItColorSchema
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.add
import split_the_bill.app.shared.generated.resources.all_splits
import split_the_bill.app.shared.generated.resources.group
import split_the_bill.app.shared.generated.resources.home
import split_the_bill.app.shared.generated.resources.reminder


@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
@Preview
fun App() {
    MaterialExpressiveTheme(
        colorScheme = splitItColorSchema,
        typography = appTypography(),
        motionScheme = MotionScheme.expressive()
    ) {
        val backStack: MutableList<Screens> = rememberSerializable(
            serializer = SnapshotStateListSerializer()
        ) {
            mutableStateListOf(Screens.Home)
        }

        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        val focusManager = LocalFocusManager.current

        Scaffold(
            modifier = Modifier
                .nestedScroll(scrollBehavior.nestedScrollConnection)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = {
                        focusManager.clearFocus()
                    })
                },
            topBar = {
                when (backStack.last()) {
                    Screens.Home -> {
                        HomeScreenTopAppBar(scrollBehavior)
                    }

                    Screens.Groups -> {
                        GroupScreenTopAppBar(scrollBehavior)
                    }

                    Screens.Splits -> {
                        SplitScreenTopAppBar(scrollBehavior)
                    }

                    Screens.Registration -> TODO()
                    Screens.Remind -> TODO()
                    Screens.Splits.Id -> TODO()
                }
            },
            bottomBar = {
                if (backStack.last() !is Screens.Registration) {
                    BottomAppBar {
                        NavigationBarItem(
                            selected = backStack.last() is Screens.Home,
                            onClick = {
                                backStack.add(Screens.Home)
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(Res.drawable.home),
                                    contentDescription = "home",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text("Home")
                            }
                        )
                        NavigationBarItem(
                            selected = backStack.last() is Screens.Splits,
                            onClick = {
                                backStack.add(Screens.Splits)
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(Res.drawable.all_splits),
                                    contentDescription = "all_split",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text("Splits")
                            }
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = {},
                            icon = {
                                FloatingActionButton(
                                    onClick = {},
                                    containerColor = MaterialTheme.colorScheme.primary,
                                    contentColor = MaterialTheme.colorScheme.onPrimary
                                ) {
                                    Icon(
                                        painter = painterResource(Res.drawable.add),
                                        contentDescription = "add",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        )
                        NavigationBarItem(
                            selected = false,
                            onClick = {},
                            icon = {
                                Icon(
                                    painter = painterResource(Res.drawable.reminder),
                                    contentDescription = "reminder",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text("Remind")
                            }
                        )
                        NavigationBarItem(
                            selected = backStack.last() is Screens.Groups,
                            onClick = {
                                backStack.add(Screens.Groups)
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(Res.drawable.group),
                                    contentDescription = "group",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            label = {
                                Text("Groups")
                            }
                        )
                    }
                }
            }
        ) { contentPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding)
            ) {
                NavDisplay(
                    transitionSpec = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(600)
                        ) togetherWith slideOutHorizontally(
                            targetOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(600)
                        )
                    },
                    popTransitionSpec = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(600)
                        ) togetherWith slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(600)
                        )
                    },
                    predictivePopTransitionSpec = {
                        slideInHorizontally(
                            initialOffsetX = { fullWidth -> -fullWidth },
                            animationSpec = tween(600)
                        ) togetherWith slideOutHorizontally(
                            targetOffsetX = { fullWidth -> fullWidth },
                            animationSpec = tween(600)
                        )
                    },
                    backStack = backStack,
                    onBack = {
                        backStack.removeLastOrNull()
                    },
                    entryProvider = entryProvider {
                        entry<Screens.Home> {
                            HomePage()
                        }
                        entry<Screens.Groups> {
                            GroupScreen().NavigationBuilder { }
                        }
                        entry<Screens.Registration> {
                            RegistrationScreen().NavigationBuilder {
                                backStack.add(Screens.Home)
                            }
                        }
                        entry<Screens.Splits> {
                            SplitScreen().NavigationBuilder {  }
                        }
                    }
                )
            }
        }
    }
}
