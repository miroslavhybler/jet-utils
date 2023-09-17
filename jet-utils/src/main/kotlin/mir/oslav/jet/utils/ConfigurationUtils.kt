package mir.oslav.jet.utils

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp


/**
 * Returns true when the screen is at least approximately 720x960 dp units, meaning the device is
 * basically a tablet, false otherwise.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
public val Configuration.isExtraLargeScreen: Boolean
    get() = Configuration.SCREENLAYOUT_SIZE_MASK.maskedEquals(
        other = Configuration.SCREENLAYOUT_SIZE_XLARGE
    )

/**
 * Returns true when the screen is at least approximately 480x640 dp units, false otherwise.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
public val Configuration.isLargeScreen: Boolean
    get() = Configuration.SCREENLAYOUT_SIZE_MASK.maskedEquals(
        other = Configuration.SCREENLAYOUT_SIZE_LARGE
    )


/**
 * Returns true when the screen is at least approximately 320x470 dp units, false otherwise.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
public val Configuration.isNormalScreen: Boolean
    get() = Configuration.SCREENLAYOUT_SIZE_MASK.maskedEquals(
        other = Configuration.SCREENLAYOUT_SIZE_NORMAL
    )


/**
 * Returns true when the screen is at least approximately 320x426 dp units, false otherwise.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
public val Configuration.isSmallScreen: Boolean
    get() = Configuration.SCREENLAYOUT_SIZE_MASK.maskedEquals(
        other = Configuration.SCREENLAYOUT_SIZE_SMALL
    )


/**
 * Returns screen width in pixels
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
public val Configuration.screenWidthPx: Float
    @Composable
    @ReadOnlyComposable
    get() {
        val density = LocalDensity.current
        return density.dpToPx(dp = this.screenWidthDp.dp)
    }


/**
 * Returns screen height in pixels
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 17.03.2023
 */
public val Configuration.screenHeightPx: Float
    @Composable
    @ReadOnlyComposable
    get() {
        val density = LocalDensity.current
        return density.dpToPx(dp = this.screenHeightDp.dp)
    }


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
context (Configuration)
private infix fun Int.maskedEquals(other: Int): Boolean = this.and(screenLayout) == other