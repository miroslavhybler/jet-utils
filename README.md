# jet-utils

Contains primitive utils that can be handy when doing Material You themed app in Jetpack Compose

### Add library to your project

**Project's settings.gradle.kts**
```kotlin
// Adds maven 
dependencyResolutionManagement {
    repositories {
        maven(url = "https://jitpack.io")
    }
}
```

**Application's module build.gradle.kts**
```kotlin
dependencies {
    implementation("com.github.miroslavhybler:jet-utils:1.1.3")
}
```

### WindowSizeClass simplification
```kotlin
val WindowWidthSizeClass.isCompat: Boolean
    get() = this == WindowWidthSizeClass.COMPACT

val WindowWidthSizeClass.isMedium: Boolean
    get() = this == WindowWidthSizeClass.MEDIUM

val WindowWidthSizeClass.isExpanded: Boolean
    get() = this == WindowWidthSizeClass.EXPANDED


val WindowHeightSizeClass.isCompat: Boolean
    get() = this == WindowHeightSizeClass.COMPACT

val WindowHeightSizeClass.isMedium: Boolean
    get() = this == WindowHeightSizeClass.MEDIUM

val WindowHeightSizeClass.isExpanded: Boolean
    get() = this == WindowHeightSizeClass.EXPANDED
```


### Configuration


### Density 
Simple functions to convert units on `Density` without need of `with` scoped function:
```agsl
val px= density.dpToPx(dp = 24.dp)
val dp = density.pxToDp(px = 24f)

```

### Insets
