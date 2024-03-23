package com.jet.utils

import androidx.annotation.ColorInt
import androidx.core.graphics.ColorUtils


/**
 * Luminance level at which is color considered to be as dark as it's possible
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
private const val totalDarkLuminance: Float = 0f


/**
 * Luminance level at which is color considered for sure to be dark
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 11.09.2023
 */
private const val darkLuminance: Float = 0.25f


/**
 * Medium Luminance level at which color can be both light or dark
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
private const val mediumLuminance: Float = 0.5f

/**
 * Luminance level at which is color considered for sure to be light
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
private const val lightLuminance: Float = 0.75f

/**
 * Luminance level at which is color considered to be as light as it's possible
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
private const val totalLightLuminance: Float = 1f


////////////////////////////////////////////////////////////////////////////////////////////////////
/////
/////   Dark Colors
/////
////////////////////////////////////////////////////////////////////////////////////////////////////


/**
 * True when color is probably light based on luminance
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
public val @receiver:ColorInt Int.isProbablyDarkColor: Boolean
    get() = ColorUtils.calculateLuminance(this).toFloat() <= mediumLuminance


/**
 * True when color is probably dark based on luminance
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 11.09.2023
 */
public val @receiver:ColorInt Int.isDarkColor: Boolean
    get() = ColorUtils.calculateLuminance(this) < darkLuminance


/**
 * True when color is probably light based on luminance
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
public val @receiver:ColorInt Int.isFullyDarkColor: Boolean
    get() = ColorUtils.calculateLuminance(this).toFloat() == totalDarkLuminance


////////////////////////////////////////////////////////////////////////////////////////////////////
/////
/////   Light Colors
/////
////////////////////////////////////////////////////////////////////////////////////////////////////


/**
 * True when color is probably light based on luminance
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
public val @receiver:ColorInt Int.isProbablyLightColor: Boolean
    get() = ColorUtils.calculateLuminance(this).toFloat() >= mediumLuminance


/**
 * True when color is probably light based on luminance
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 11.09.2023
 */
public val @receiver:ColorInt Int.isLightColor: Boolean
    get() = ColorUtils.calculateLuminance(this) >= lightLuminance


/**
 * True when color is probably light based on luminance
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
public val @receiver:ColorInt Int.isFullyLightColor: Boolean
    get() = ColorUtils.calculateLuminance(this).toFloat() == totalLightLuminance
