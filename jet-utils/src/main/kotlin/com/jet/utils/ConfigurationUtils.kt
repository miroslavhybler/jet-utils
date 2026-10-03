package com.jet.utils

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp


/**
 * Returns true when the legacy configuration size bucket is exactly extra large.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
@Deprecated(message = "Use material3 window size class instead")
public val Configuration.isExtraLargeScreen: Boolean
    get() = Configuration.SCREENLAYOUT_SIZE_MASK.maskedEquals(
        other = Configuration.SCREENLAYOUT_SIZE_XLARGE
    )

/**
 * Returns true when the legacy configuration size bucket is exactly large.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
@Deprecated(message = "Use material3 window size class instead")
public val Configuration.isLargeScreen: Boolean
    get() = Configuration.SCREENLAYOUT_SIZE_MASK.maskedEquals(
        other = Configuration.SCREENLAYOUT_SIZE_LARGE
    )


/**
 * Returns true when the legacy configuration size bucket is exactly normal.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
@Deprecated(message = "Use material3 window size class instead")
public val Configuration.isNormalScreen: Boolean
    get() = Configuration.SCREENLAYOUT_SIZE_MASK.maskedEquals(
        other = Configuration.SCREENLAYOUT_SIZE_NORMAL
    )


/**
 * Returns true when the legacy configuration size bucket is exactly small.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
@Deprecated(message = "Use material3 window size class instead")
public val Configuration.isSmallScreen: Boolean
    get() = Configuration.SCREENLAYOUT_SIZE_MASK.maskedEquals(
        other = Configuration.SCREENLAYOUT_SIZE_SMALL
    )


/**
 * Returns the configuration width converted to pixels.
 *
 * This can differ from the actual Compose container in multi-window and resizable layouts.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
@Deprecated(
    message = "Configuration.screenWidthDp can differ from the actual Compose window. Use LocalWindowInfo.current.containerSize.width instead.",
    replaceWith = ReplaceWith(
        expression = "LocalWindowInfo.current.containerSize.width.toFloat()",
        imports = ["androidx.compose.ui.platform.LocalWindowInfo"],
    ),
)
@get:SuppressLint("ConfigurationScreenWidthHeight")
public val Configuration.screenWidthPx: Float
    @Composable
    @ReadOnlyComposable
    get() {
        val density = LocalDensity.current
        return density.dpToPx(dp = this.screenWidthDp.dp)
    }


/**
 * Returns the configuration height converted to pixels.
 *
 * This can differ from the actual Compose container in multi-window and resizable layouts.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
@Deprecated(
    message = "Configuration.screenHeightDp can differ from the actual Compose window. Use LocalWindowInfo.current.containerSize.height instead.",
    replaceWith = ReplaceWith(
        expression = "LocalWindowInfo.current.containerSize.height.toFloat()",
        imports = ["androidx.compose.ui.platform.LocalWindowInfo"],
    ),
)
@get:SuppressLint("ConfigurationScreenWidthHeight")
public val Configuration.screenHeightPx: Float
    @Composable
    @ReadOnlyComposable
    get() {
        val density = LocalDensity.current
        return density.dpToPx(dp = this.screenHeightDp.dp)
    }


/**
 * True when actual screen orientation is portrait (vertical).
 * @since 1.0.2
 */
@Deprecated(message = "Don't use isPortrait to determine device orientation, use material3 window size class instead")
public val Configuration.isPortrait: Boolean
    get() = this.orientation == Configuration.ORIENTATION_PORTRAIT


/**
 * True when actual screen orientation is landscape (horizontal).
 * @since 1.0.2
 */
@Deprecated(message = "Don't use isLandscape to determine device orientation, use material3 window size class instead")
public val Configuration.isLandScape: Boolean
    get() = this.orientation == Configuration.ORIENTATION_LANDSCAPE


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
context(configuration: Configuration)
private infix fun Int.maskedEquals(other: Int): Boolean {
    return this.and(other = configuration.screenLayout) == other
}
