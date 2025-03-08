package com.jet.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


/**
 * @param event [Lifecycle.Event] you want to intercept
 * @param block Callback which will be executed when new lifecycle's event is [event]
 * @author Miroslav Hýbler <br>
 * created on 10.05.2024
 * @since 1.1.3
 */
@Composable
fun OnLifecycleLauchedEffect(
    event: Lifecycle.Event,
    block: suspend CoroutineScope.() -> Unit
) {
    val lifecycleOwner = rememberUpdatedState(newValue = LocalLifecycleOwner.current)
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(key1 = lifecycleOwner.value) {
        val lifecycle = lifecycleOwner.value.lifecycle
        val observer = LifecycleEventObserver { owner, newEvent ->
            if (newEvent == event) {
                coroutineScope.launch {
                    block(this)
                }
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
}