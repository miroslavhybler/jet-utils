package com.jet.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


/**
 * Can be used to preview how safe padding modifier works.
 * @author Miroslav Hýbler <br>
 * created on 10.04.2024
 * @since 1.1.1
 */
@Composable
fun SafePaddingsPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = "safeDrawingPadding()",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.align(alignment = Alignment.TopCenter)
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .safeGesturesPadding()
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Text(
                    text = "safeGesturesPadding()",
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.align(alignment = Alignment.TopCenter)
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .safeContentPadding()
                        .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                ) {
                    Text(
                        text = "safeContentPadding()",
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.align(alignment = Alignment.TopCenter)
                    )
                }
            }
        }
    }

}