package com.system.screen.groups.layoutComponent

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SearchBarValue
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.backward
import split_the_bill.app.shared.generated.resources.close
import split_the_bill.app.shared.generated.resources.search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreenTopAppBar(
    focusManager: FocusManager,
    onClear: Int
) {
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()
    val scope = rememberCoroutineScope()
    val focusRequester = remember { FocusRequester() }
    val isExpanded by remember {
        derivedStateOf { searchBarState.currentValue == SearchBarValue.Expanded }
    }


    LaunchedEffect(onClear) {
        scope.launch { searchBarState.animateToCollapsed() }
    }

    LaunchedEffect(isExpanded) {
        if (isExpanded) {
            focusRequester.requestFocus()
        } else {
            focusManager.clearFocus()
        }
    }

    val inputField = @Composable {
        SearchBarDefaults.InputField(
            textFieldState = textFieldState,
            searchBarState = searchBarState,
            onSearch = { scope.launch { searchBarState.animateToCollapsed() } },
            modifier = Modifier.focusRequester(focusRequester),
            placeholder = {
                Text(modifier = Modifier.clearAndSetSemantics {}, text = "Search")
            },
            leadingIcon = {
                if (!isExpanded) {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(Res.drawable.search),
                            contentDescription = "Search",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                } else {
                    IconButton(onClick = {
                        scope.launch { searchBarState.animateToCollapsed() }
                        focusManager.clearFocus()  // ✅ clear focus on back
                    }) {
                        Icon(
                            painter = painterResource(Res.drawable.backward),
                            contentDescription = "back",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            },
            trailingIcon = {
                AnimatedVisibility(visible = isExpanded) {
                    IconButton(onClick = { textFieldState.clearText() }) {
                        Icon(
                            painter = painterResource(Res.drawable.close),
                            contentDescription = "close",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        )
    }

    val widthFraction by animateFloatAsState(
        targetValue = if (isExpanded) 1f else 0.95f,
        animationSpec = tween(300)
    )

    TopAppBar(
        title = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SearchBar(
                    state = searchBarState,
                    inputField = inputField,
                    modifier = Modifier
                        .fillMaxWidth(widthFraction)
                        .padding(end = 7.dp)
                )
            }
        }
    )
}