package mir.oslav.jet.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
public fun Density.pxToDp(px: Float): Dp = px.toDp()


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
public fun Density.pxToDp(px: Int): Dp = px.toDp()


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
public fun Density.dpToPx(dp: Dp): Float = dp.toPx()


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
public fun Density.statusBarsPadding(): Dp {
    return statusBarsPaddingPx().toDp()
}


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 02.09.2023
 */
@Composable
public fun Density.statusBarsPaddingPx(): Int {
    return WindowInsets.statusBars.getTop(density = this)
}


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
public fun Density.navigationBarsPadding(): Dp {
    return navigationBarsPaddingPx().toDp()
}


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 02.09.2023
 */
@Composable
public fun Density.navigationBarsPaddingPx(): Int {
    return WindowInsets.navigationBars.getBottom(density = this)
}


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
public fun Density.imePadding(): Dp {
    return imePaddingPx().toDp()
}


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
public fun Density.imePaddingPx(): Int {
    return WindowInsets.ime.getBottom(density = this)
}