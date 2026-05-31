package com.system.screen.registration

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.system.theme.jetBrainsMonoFontFamily
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.firstname
import split_the_bill.app.shared.generated.resources.lastname
import split_the_bill.app.shared.generated.resources.username
import split_the_bill.app.shared.generated.resources.close

@Composable
fun BasicInformationPage(
    nextPage: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

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
            LazyColumn {

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "BASIC INFORMATION",
                            style = MaterialTheme.typography.headlineLarge,
                            fontFamily = jetBrainsMonoFontFamily(),
                            fontWeight = FontWeight.Black,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "Easy way to split bill among group of people.",
                            textAlign = TextAlign.Center
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    var isFocused by remember { mutableStateOf(false) }
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = { firstName = it },
                        label = { Text("First Name") },
                        supportingText = { Text("Enter your first name") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(Res.drawable.firstname),
                                contentDescription = "fistName",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            if (isFocused && firstName.isNotEmpty()) {
                                IconButton(onClick = { firstName = "" }) {
                                    Icon(
                                        painter = painterResource(Res.drawable.close),
                                        contentDescription = "close",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },
                        singleLine = true
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    var isFocused by remember { mutableStateOf(false) }
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = { lastName = it },
                        label = { Text("Last Name") },
                        supportingText = { Text("Enter your last name") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(Res.drawable.lastname),
                                contentDescription = "lastname",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            if (isFocused && lastName.isNotEmpty()) {
                                Icon(
                                    painter = painterResource(Res.drawable.close),
                                    contentDescription = "close",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },
                        singleLine = true
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(8.dp))
                }

                item {
                    var isFocused by remember { mutableStateOf(false) }
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = { Text("User Name") },
                        supportingText = { Text("Enter your user name. This will be use for others to see") },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(Res.drawable.username),
                                contentDescription = "userName",
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            if (isFocused && userName.isNotEmpty()) {
                                IconButton(onClick = { userName = "" }) {
                                    Icon(
                                        painter = painterResource(Res.drawable.close),
                                        contentDescription = "close",
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .onFocusChanged {
                                isFocused = it.isFocused
                            },
                        singleLine = true
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(24.dp))
                }

                item {
                    Button(
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary
                        ),
                        onClick = {
                            nextPage()
                        }
                    ) {
                        Text("Begin Creating My Account")
                    }
                }
            }
        }
    }
}