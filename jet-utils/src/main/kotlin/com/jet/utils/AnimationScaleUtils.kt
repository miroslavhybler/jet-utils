package com.jet.utils

import android.content.Context
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


/**
 * Returns current windowAnimationScale that can be enabled by developer options. Default value is 1.0f.
 * This can be used to adjust custom animation durations and delays when debugging.
 * @author Miroslav Hýbler <br>
 * created on 09.04.2024
 * @since 1.1.1
 */
val Context.windowAnimationScale: Float
    get() {
        return Settings.Global.getFloat(
            this.contentResolver,
            Settings.Global.ANIMATOR_DURATION_SCALE, 1.0f
        )
    }


/**
 * @return [windowAnimationScale]
 * @since 1.1.1
 */
@Composable
fun getWindowAnimationScale(): Float {
    val context = LocalContext.current
    return context.windowAnimationScale
}