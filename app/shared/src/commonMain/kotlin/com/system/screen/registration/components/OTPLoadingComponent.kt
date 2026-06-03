package com.system.screen.registration.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.system.theme.jetBrainsMonoFontFamily

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Preview
@Composable
fun OTPLoadingComponent() {
    LazyColumn {
        item {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularWavyProgressIndicator(
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    text = "VALIDATING OTP",
                    style = MaterialTheme.typography.headlineLarge,
                    fontFamily = jetBrainsMonoFontFamily(),
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = "Validating one time password you provide. Please wait for fix seconds",
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}