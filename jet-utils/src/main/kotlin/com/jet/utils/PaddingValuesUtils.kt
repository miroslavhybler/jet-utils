package com.jet.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLayoutDirection


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

    return remember(key1 = this, key2 = other) {
        val start = this.calculateStartPadding(layoutDirection = layoutDirection)
            .plus(other = other.calculateStartPadding(layoutDirection))
        val top = this.calculateTopPadding() + other.calculateTopPadding()
        val end = this.calculateEndPadding(layoutDirection = layoutDirection)
            .plus(other = other.calculateEndPadding(layoutDirection = layoutDirection))
        val bottom = this.calculateBottomPadding() + other.calculateBottomPadding()

        return@remember PaddingValues(start = start, top = top, end = end, bottom = bottom)
    }
}
