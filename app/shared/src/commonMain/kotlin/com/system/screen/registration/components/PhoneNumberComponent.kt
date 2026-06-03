package com.system.screen.registration.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import split_the_bill.app.shared.generated.resources.Res
import split_the_bill.app.shared.generated.resources.close

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhoneNumberComponent(
    selectedCountryCode: String,
    onSelectedCountryCodeChange: (String) -> Unit,
    onSelectedCountryCodeError: String? = null,
    phoneNumber: String,
    onPhoneNumberChange: (String) -> Unit,
    onPhoneNumberClear: () -> Unit,
    onPhoneNumberError: String? = null
) {

    var expanded by remember { mutableStateOf(false) }

    val countryCodes = listOf(
        "LK (+94)", "US (+1)", "GB (+44)", "IN (+91)", "CA (+1)", "AU (+61)",
        "DE (+49)", "FR (+33)", "JP (+81)", "CN (+86)", "BR (+55)"
    )

    var isFocused by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(0.4f)
        ) {
            OutlinedTextField(
                isError = onSelectedCountryCodeError != null,
                value = selectedCountryCode,
                onValueChange = {},
                readOnly = true,
                label = { Text("Code") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
                supportingText = {
                    if (onSelectedCountryCodeError == null) {
                        Text("Country code")
                    } else {
                        Text(onSelectedCountryCodeError)
                    }
                }
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                containerColor = MaterialTheme.colorScheme.background
            ) {
                countryCodes.forEach { code ->
                    DropdownMenuItem(
                        text = { Text(code) },
                        onClick = {
                            onSelectedCountryCodeChange(code)
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { onPhoneNumberChange(it) },
            label = { Text("Phone Number") },
            isError = onPhoneNumberError != null,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.weight(0.6f)
                .onFocusChanged {
                    isFocused = it.isFocused
                },
            maxLines = 1,
            trailingIcon = {
                if (isFocused && phoneNumber.isNotEmpty()) {
                    IconButton(
                        onClick = { onPhoneNumberClear() }
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.close),
                            contentDescription = "close",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            },
            supportingText = {
                if (onPhoneNumberError == null) {
                    Text("Phone number")
                } else {
                    Text(onPhoneNumberError)
                }
            }
        )
    }
}