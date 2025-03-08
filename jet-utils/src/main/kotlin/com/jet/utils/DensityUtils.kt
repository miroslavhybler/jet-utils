@file:Suppress("unused")

package com.jet.utils

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit


/**
 * @param px Input value in pixels
 * @return Value of [px] in dp
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
public fun Density.pxToDp(px: Float): Dp = px.toDp()


/**
 * @param px Input value in pixels
 * @return Value of [px] in dp
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
public fun Density.pxToDp(px: Int): Dp = px.toDp()


/**
 * @param dp Input value in dp units
 * @return Value of [dp] in pixels
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
public fun Density.dpToPx(dp: Dp): Float = dp.toPx()


/**
 * @return Height of status bar id dp units.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
public fun Density.statusBarsPadding(): Dp {
    return statusBarsPaddingPx().toDp()
}


/**
 * @return Height of status bar in pixels.
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 02.09.2023
 */
@Composable
public fun Density.statusBarsPaddingPx(): Int {
    return WindowInsets.statusBars.getTop(density = this)
}


/**
 * @return Navigation bar height in dp units
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
public fun Density.navigationBarsPadding(): Dp {
    return navigationBarsPaddingPx().toDp()
}


/**
 * @return Navigation bar height in pixels
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 02.09.2023
 */
@Composable
public fun Density.navigationBarsPaddingPx(): Int {
    return WindowInsets.navigationBars.getBottom(density = this)
}


/**
 * @return Ime height (incuding navigation bar too) in dp units
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
public fun Density.imePadding(): Dp {
    return imePaddingPx().toDp()
}


/**
 * @return Ime height (incuding navigation bar too) in pixels
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
public fun Density.imePaddingPx(): Int {
    return WindowInsets.ime.getBottom(density = this)
}