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
    implementation("com.github.miroslavhybler:jet-utils:1.3.2")
}
```

## What the library includes

The public API currently lives in:

- `com.jet.utils`
- `com.jet.utils.adaptive`
- `com.jet.utils.theme`

### Legacy adaptive window size helpers

These shortcuts remain available for compatibility, but their AndroidX receiver types are
deprecated. New code should use `WindowSizeClass.isWidthAtLeastBreakpoint(...)` and
`isHeightAtLeastBreakpoint(...)`, evaluating breakpoints from largest to smallest.

```kotlin
import com.jet.utils.adaptive.isCompat
import com.jet.utils.adaptive.isExpanded
import com.jet.utils.adaptive.isMedium

@Deprecated("Use WindowSizeClass.isWidthAtLeastBreakpoint")
val WindowWidthSizeClass.isCompat: Boolean
@Deprecated("Use WindowSizeClass.isWidthAtLeastBreakpoint")
val WindowWidthSizeClass.isMedium: Boolean
@Deprecated("Use WindowSizeClass.isWidthAtLeastBreakpoint")
val WindowWidthSizeClass.isExpanded: Boolean

@Deprecated("Use WindowSizeClass.isHeightAtLeastBreakpoint")
val WindowHeightSizeClass.isCompat: Boolean
@Deprecated("Use WindowSizeClass.isHeightAtLeastBreakpoint")
val WindowHeightSizeClass.isMedium: Boolean
@Deprecated("Use WindowSizeClass.isHeightAtLeastBreakpoint")
val WindowHeightSizeClass.isExpanded: Boolean
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

These compatibility helpers read `Settings.Global.ANIMATOR_DURATION_SCALE`. Their names mention
window animation scale, and the returned value is not observable, so both APIs are deprecated.
Compose animation APIs already respect the system animator duration scale.

```kotlin
import com.jet.utils.getWindowAnimationScale
import com.jet.utils.windowAnimationScale

@Deprecated("Prefer Compose animation APIs")
val Context.windowAnimationScale: Float

@Composable
@Deprecated("Prefer Compose animation APIs")
fun getWindowAnimationScale(): Float
```

### ColorInt luminance checks

Luminance-based helpers for opaque `@ColorInt Int` values. The AndroidX luminance calculation uses
the RGB channels and does not composite alpha against a background.

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
@Deprecated("A ColorScheme cannot reproduce its source tonal palettes")
fun MaterialColorSchemeTonesPreview()

@Composable
fun MaterialTypographyPreview()
```

### Configuration helpers

The legacy configuration pixel helpers remain available for compatibility. They can report the
wrong dimensions in multi-window and resizable layouts, so new Compose code should use the actual
window container size:

```kotlin
import androidx.compose.ui.platform.LocalWindowInfo

val windowSizePx = LocalWindowInfo.current.containerSize
```

Available helpers:

```kotlin
@Deprecated("Use LocalWindowInfo.current.containerSize.width")
val Configuration.screenWidthPx: Float

@Deprecated("Use LocalWindowInfo.current.containerSize.height")
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
