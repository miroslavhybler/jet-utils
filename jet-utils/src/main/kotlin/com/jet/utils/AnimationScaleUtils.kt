package com.jet.utils

import android.content.Context
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext


/**
 * Returns current windowAnimationScale that can be enabled by developer options. Default value is 1.0f.
 * This can be used to adjust custom animation durations and delays when debugging.
 * @author Miroslav Hýbler <br>
 * created on 09.04.2024
 * @since 1.1.1
 */
@Deprecated(
    message = "Despite its name, this reads Settings.Global.ANIMATOR_DURATION_SCALE and does not observe changes. Prefer Compose animation APIs, which already respect the system animator duration scale.",
)
val Context.windowAnimationScale: Float
    get() = readAnimatorDurationScale()


/**
 * @return [windowAnimationScale]
 * @since 1.1.1
 */
@Composable
@Deprecated(
    message = "This value is not observable and the function name is misleading. Prefer Compose animation APIs, which already respect the system animator duration scale.",
)
fun getWindowAnimationScale(): Float {
    val context = LocalContext.current
    return context.readAnimatorDurationScale()
}


private fun Context.readAnimatorDurationScale(): Float {
    return Settings.Global.getFloat(
        contentResolver,
        Settings.Global.ANIMATOR_DURATION_SCALE,
        1.0f,
    )
}
