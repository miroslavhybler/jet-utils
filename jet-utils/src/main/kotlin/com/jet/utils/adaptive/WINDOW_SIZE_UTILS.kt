@file:Suppress("DEPRECATION")

package com.jet.utils.adaptive

import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
@Deprecated(
    message = "WindowWidthSizeClass is deprecated by AndroidX. Use WindowSizeClass.isWidthAtLeastBreakpoint and process breakpoints from largest to smallest.",
)
val WindowWidthSizeClass.isCompat: Boolean
    get() = this == WindowWidthSizeClass.COMPACT


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
@Deprecated(
    message = "WindowWidthSizeClass is deprecated by AndroidX. Use WindowSizeClass.isWidthAtLeastBreakpoint and process breakpoints from largest to smallest.",
)
val WindowWidthSizeClass.isMedium: Boolean
    get() = this == WindowWidthSizeClass.MEDIUM


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
@Deprecated(
    message = "WindowWidthSizeClass is deprecated by AndroidX. Use WindowSizeClass.isWidthAtLeastBreakpoint and process breakpoints from largest to smallest.",
)
val WindowWidthSizeClass.isExpanded: Boolean
    get() = this == WindowWidthSizeClass.EXPANDED


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
@Deprecated(
    message = "WindowHeightSizeClass is deprecated by AndroidX. Use WindowSizeClass.isHeightAtLeastBreakpoint and process breakpoints from largest to smallest.",
)
val WindowHeightSizeClass.isCompat: Boolean
    get() = this == WindowHeightSizeClass.COMPACT


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
@Deprecated(
    message = "WindowHeightSizeClass is deprecated by AndroidX. Use WindowSizeClass.isHeightAtLeastBreakpoint and process breakpoints from largest to smallest.",
)
val WindowHeightSizeClass.isMedium: Boolean
    get() = this == WindowHeightSizeClass.MEDIUM


/**
 * Used for shortening the code while using [androidx.window.core.layout.WindowSizeClass]
 * @since 1.2.0
 */
@Deprecated(
    message = "WindowHeightSizeClass is deprecated by AndroidX. Use WindowSizeClass.isHeightAtLeastBreakpoint and process breakpoints from largest to smallest.",
)
val WindowHeightSizeClass.isExpanded: Boolean
    get() = this == WindowHeightSizeClass.EXPANDED
