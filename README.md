# jet-utils

`jet-utils` is a small Jetpack Compose helper library focused on the bits you reach for often when building Material 3 / adaptive UIs: window size shortcuts, density and insets helpers, padding composition, color checks, and preview utilities.

## Add library to your project

**Project `settings.gradle.kts`**

```kotlin
dependencyResolutionManagement {
    repositories {
        maven(url = "https://jitpack.io")
    }
}
```

**App or feature module `build.gradle.kts`**

```kotlin
dependencies {
    implementation("com.github.miroslavhybler:jet-utils:1.3.1")
}
```

## What the library includes

The public API currently lives in:

- `com.jet.utils`
- `com.jet.utils.adaptive`
- `com.jet.utils.theme`

### Adaptive window size helpers

Shortcuts for `androidx.window.core.layout.WindowWidthSizeClass` and `WindowHeightSizeClass`.

```kotlin
import com.jet.utils.adaptive.isCompat
import com.jet.utils.adaptive.isExpanded
import com.jet.utils.adaptive.isMedium

val WindowWidthSizeClass.isCompat: Boolean
val WindowWidthSizeClass.isMedium: Boolean
val WindowWidthSizeClass.isExpanded: Boolean

val WindowHeightSizeClass.isCompat: Boolean
val WindowHeightSizeClass.isMedium: Boolean
val WindowHeightSizeClass.isExpanded: Boolean
```

Example:

```kotlin
if (windowSizeClass.windowWidthSizeClass.isExpanded) {
    // tablet / desktop-like layout
}
```

### Density and inset helpers

Extensions on `Density` for unit conversion and reading common system insets.

```kotlin
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import com.jet.utils.dpToPx
import com.jet.utils.imePadding
import com.jet.utils.imePaddingPx
import com.jet.utils.navigationBarsPadding
import com.jet.utils.navigationBarsPaddingPx
import com.jet.utils.pxToDp
import com.jet.utils.statusBarsPadding
import com.jet.utils.statusBarsPaddingPx

val px = with(LocalDensity.current) { dpToPx(24.dp) }
val dp = with(LocalDensity.current) { pxToDp(24f) }
```

Available helpers:

```kotlin
fun Density.pxToDp(px: Float): Dp
fun Density.pxToDp(px: Int): Dp
fun Density.dpToPx(dp: Dp): Float

@Composable
fun Density.statusBarsPadding(): Dp

@Composable
fun Density.statusBarsPaddingPx(): Int

@Composable
fun Density.navigationBarsPadding(): Dp

@Composable
fun Density.navigationBarsPaddingPx(): Int

@Composable
fun Density.imePadding(): Dp

@Composable
fun Density.imePaddingPx(): Int
```

### PaddingValues utilities

Helpers for composing or adjusting `PaddingValues` without unpacking each edge manually.

```kotlin
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.dp
import com.jet.utils.copy
import com.jet.utils.plus

@Composable
fun example(scaffoldPadding: PaddingValues): PaddingValues {
    val contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)

    return (scaffoldPadding + contentPadding).copy(bottom = 24.dp)
}
```

Available helpers:

```kotlin
@Composable
infix operator fun PaddingValues.plus(other: PaddingValues): PaddingValues

@Composable
fun PaddingValues.copy(
    start: Dp? = null,
    top: Dp? = null,
    end: Dp? = null,
    bottom: Dp? = null,
): PaddingValues
```

### Animation scale utilities

Read the system animator duration scale so custom animations can react to developer options.

```kotlin
import com.jet.utils.getWindowAnimationScale
import com.jet.utils.windowAnimationScale

val Context.windowAnimationScale: Float

@Composable
fun getWindowAnimationScale(): Float
```

Example:

```kotlin
val animationScale = getWindowAnimationScale()
```

### ColorInt luminance checks

Luminance-based helpers for `@ColorInt Int` values.

```kotlin
import androidx.annotation.ColorInt
import com.jet.utils.isDarkColor
import com.jet.utils.isFullyDarkColor
import com.jet.utils.isFullyLightColor
import com.jet.utils.isLightColor
import com.jet.utils.isProbablyDarkColor
import com.jet.utils.isProbablyLightColor

val @receiver:ColorInt Int.isProbablyDarkColor: Boolean
val @receiver:ColorInt Int.isDarkColor: Boolean
val @receiver:ColorInt Int.isFullyDarkColor: Boolean

val @receiver:ColorInt Int.isProbablyLightColor: Boolean
val @receiver:ColorInt Int.isLightColor: Boolean
val @receiver:ColorInt Int.isFullyLightColor: Boolean
```

### Theme and preview utilities

Composable previews for checking Material 3 color roles, tonal palettes, typography, and safe-area padding behavior.

```kotlin
import com.jet.utils.SafePaddingsPreview
import com.jet.utils.theme.MaterialColorSchemePreview
import com.jet.utils.theme.MaterialColorSchemeTonesPreview
import com.jet.utils.theme.MaterialColorsPreview
import com.jet.utils.theme.MaterialTypography
import com.jet.utils.theme.MaterialTypographyPreview

@Composable
fun SafePaddingsPreview()

@Composable
fun MaterialColorSchemePreview()

@Composable
fun MaterialColorSchemeTonesPreview()

@Composable
fun MaterialTypographyPreview()
```

### Configuration helpers

Helpers on `android.content.res.Configuration` for screen size and pixel conversions.

```kotlin
import androidx.compose.ui.platform.LocalConfiguration
import com.jet.utils.screenHeightPx
import com.jet.utils.screenWidthPx

val configuration = LocalConfiguration.current
val widthPx = configuration.screenWidthPx
val heightPx = configuration.screenHeightPx
```

Available helpers:

```kotlin
val Configuration.screenWidthPx: Float
val Configuration.screenHeightPx: Float
```

Legacy helpers still shipped by the library:

```kotlin
@Deprecated("Use material3 window size class instead")
val Configuration.isExtraLargeScreen: Boolean

@Deprecated("Use material3 window size class instead")
val Configuration.isLargeScreen: Boolean

@Deprecated("Use material3 window size class instead")
val Configuration.isNormalScreen: Boolean

@Deprecated("Use material3 window size class instead")
val Configuration.isSmallScreen: Boolean

@Deprecated("Don't use isPortrait to determine device orientation, use material3 window size class instead")
val Configuration.isPortrait: Boolean

@Deprecated("Don't use isLandscape to determine device orientation, use material3 window size class instead")
val Configuration.isLandScape: Boolean
```

## Notes

- The library targets Jetpack Compose and includes Material 3 plus adaptive dependencies.
- Some helpers are intentionally deprecated because the recommended Compose / Material APIs have evolved, but they remain available for existing codebases.
