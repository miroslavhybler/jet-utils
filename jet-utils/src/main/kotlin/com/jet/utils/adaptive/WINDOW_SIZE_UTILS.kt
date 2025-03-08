package com.jet.utils.adaptive

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
val WindowWidthSizeClass.isCompat: Boolean
    get() = this == WindowWidthSizeClass.COMPACT


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
val WindowWidthSizeClass.isMedium: Boolean
    get() = this == WindowWidthSizeClass.MEDIUM


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
val WindowWidthSizeClass.isExpanded: Boolean
    get() = this == WindowWidthSizeClass.EXPANDED


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
val WindowHeightSizeClass.isCompat: Boolean
    get() = this == WindowHeightSizeClass.COMPACT


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
val WindowHeightSizeClass.isMedium: Boolean
    get() = this == WindowHeightSizeClass.MEDIUM


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
val WindowHeightSizeClass.isExpanded: Boolean
    get() = this == WindowHeightSizeClass.EXPANDED
