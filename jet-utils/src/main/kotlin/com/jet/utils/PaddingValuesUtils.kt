package com.jet.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp


/**
 * Allows to combine multiple [PaddingValues] by plus operator.
 * @author Miroslav Hýbler <br>
 * created on 19.04.2024
 * @since 1.1.2
 */
@Composable
public infix operator fun PaddingValues.plus(
    other: PaddingValues
): PaddingValues {
    val layoutDirection = LocalLayoutDirection.current

    val start = calculateStartPadding(layoutDirection = layoutDirection) +
        other.calculateStartPadding(layoutDirection = layoutDirection)
    val top = calculateTopPadding() + other.calculateTopPadding()
    val end = calculateEndPadding(layoutDirection = layoutDirection) +
        other.calculateEndPadding(layoutDirection = layoutDirection)
    val bottom = calculateBottomPadding() + other.calculateBottomPadding()

    return PaddingValues(start = start, top = top, end = end, bottom = bottom)
}


/**
 * Creates new instance of [PaddingValues] with new values or value from this instance when parameter
 * is null.
 * @param start New start padding value, using [androidx.compose.ui.unit.LayoutDirection]
 * @param top New top padding value.
 * @param end New end padding value, using [androidx.compose.ui.unit.LayoutDirection]
 * @param bottom New bottom padding value.
 * @since 1.3.1
 */
@Composable
fun PaddingValues.copy(
    start: Dp? = null,
    top: Dp? = null,
    end: Dp? = null,
    bottom: Dp? = null,
): PaddingValues {
    val direction = LocalLayoutDirection.current
    val startResolved = start ?: this.calculateStartPadding(layoutDirection = direction)
    val topResolved = top ?: this.calculateTopPadding()
    val endResolved = end ?: this.calculateEndPadding(layoutDirection = direction)
    val bottomResolved = bottom ?: this.calculateBottomPadding()

    return PaddingValues(
        start = startResolved,
        top = topResolved,
        end = endResolved,
        bottom = bottomResolved,
    )
}
