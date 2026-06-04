package com.system

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.compose.serialization.serializers.SnapshotStateListSerializer
import com.system.navigation.screens.Screens
import com.system.screen.home.HomePage
import com.system.screen.registration.screen.RegistrationScreen
import com.system.theme.appTypography
import com.system.theme.splitItColorSchema
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

        Scaffold(
            bottomBar = {
                if (backStack.last() !is Screens.Registration) {
                    BottomAppBar {
                        NavigationBarItem(
                            selected = false,
                            onClick = {},
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
                            selected = false,
                            onClick = {},
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
                            selected = false,
                            onClick = {},
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
        ) {

            NavDisplay(
                backStack = backStack,
                onBack = {
                    backStack.removeLastOrNull()
                },
                entryProvider = entryProvider {
                    entry<Screens.Home> {
                        HomePage {
                            backStack.add(Screens.Registration)
                        }
                    }
                    entry<Screens.Registration> {
                        RegistrationScreen().NavigationBuilder()
                    }
                }
            )

        }
    }
}
