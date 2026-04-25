@file:Suppress("DEPRECATION")

package com.jet.utils.example

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowHeightSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.jet.utils.copy
import com.jet.utils.dpToPx
import com.jet.utils.getWindowAnimationScale
import com.jet.utils.imePadding
import com.jet.utils.imePaddingPx
import com.jet.utils.isDarkColor
import com.jet.utils.isExtraLargeScreen
import com.jet.utils.isFullyDarkColor
import com.jet.utils.isFullyLightColor
import com.jet.utils.isLandScape
import com.jet.utils.isLargeScreen
import com.jet.utils.isLightColor
import com.jet.utils.isNormalScreen
import com.jet.utils.isPortrait
import com.jet.utils.isProbablyDarkColor
import com.jet.utils.isProbablyLightColor
import com.jet.utils.isSmallScreen
import com.jet.utils.navigationBarsPadding
import com.jet.utils.navigationBarsPaddingPx
import com.jet.utils.plus
import com.jet.utils.pxToDp
import com.jet.utils.screenHeightPx
import com.jet.utils.screenWidthPx
import com.jet.utils.statusBarsPadding
import com.jet.utils.statusBarsPaddingPx
import com.jet.utils.theme.MaterialColorSchemePreview
import com.jet.utils.theme.MaterialColorSchemeTonesPreview
import com.jet.utils.theme.MaterialTypographyPreview
import com.jet.utils.windowAnimationScale
import com.jet.utils.adaptive.isCompat
import com.jet.utils.adaptive.isExpanded
import com.jet.utils.adaptive.isMedium
import kotlinx.serialization.Serializable

private val ShowcaseCardShape = RoundedCornerShape(size = 24.dp)
private val ShowcaseInnerShape = RoundedCornerShape(size = 18.dp)

private sealed interface ShowcaseDestination : NavKey

@Serializable
private object ShowcaseHome : ShowcaseDestination

@Serializable
private object ShowcaseLayoutAndInsets : ShowcaseDestination

@Serializable
private object ShowcaseAdaptiveAndMotion : ShowcaseDestination

@Serializable
private object ShowcaseThemeAndColor : ShowcaseDestination

@Composable
fun JetUtilsShowcaseApp() {
    val context = LocalContext.current
    val activity = context.findActivity()
    val backStack = rememberNavBackStack(ShowcaseHome)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        NavDisplay(
            backStack = backStack,
            modifier = Modifier.fillMaxSize(),
            entryDecorators = listOf(rememberSaveableStateHolderNavEntryDecorator()),
            onBack = {
                if (backStack.size > 1) {
                    backStack.removeLastOrNull()
                } else {
                    activity?.finish()
                }
            },
            entryProvider =
                entryProvider<NavKey>({ key ->
                    NavEntry(key) {
                        Text(
                            text = "Unknown destination: $key",
                            modifier = Modifier.padding(all = 24.dp),
                        )
                    }
                }) {
                    entry<ShowcaseHome> {
                        HomeScreen(
                            onOpenLayoutAndInsets = { backStack.add(ShowcaseLayoutAndInsets) },
                            onOpenAdaptiveAndMotion = { backStack.add(ShowcaseAdaptiveAndMotion) },
                            onOpenThemeAndColor = { backStack.add(ShowcaseThemeAndColor) },
                        )
                    }
                    entry<ShowcaseLayoutAndInsets> {
                        ShowcaseScaffold(
                            title = "Layout & Insets",
                            subtitle = "Density helpers, insets, padding operators, and safe-area preview",
                            onBack = { backStack.removeLastOrNull() },
                        ) {
                            LayoutAndInsetsScreen()
                        }
                    }
                    entry<ShowcaseAdaptiveAndMotion> {
                        ShowcaseScaffold(
                            title = "Adaptive & Motion",
                            subtitle = "Window size shortcuts, configuration metrics, and animation scale",
                            onBack = { backStack.removeLastOrNull() },
                        ) {
                            AdaptiveAndMotionScreen()
                        }
                    }
                    entry<ShowcaseThemeAndColor> {
                        ShowcaseScaffold(
                            title = "Theme & Color",
                            subtitle = "Typography, Material color roles, tonal ramps, and ColorInt checks",
                            onBack = { backStack.removeLastOrNull() },
                        ) {
                            ThemeAndColorScreen()
                        }
                    }
                },
        )
    }
}

@Composable
private fun HomeScreen(
    onOpenLayoutAndInsets: () -> Unit,
    onOpenAdaptiveAndMotion: () -> Unit,
    onOpenThemeAndColor: () -> Unit,
) {
    val isDarkTheme = androidx.compose.foundation.isSystemInDarkTheme()

    ShowcaseScaffold(
        title = "jet-utils showcase",
        subtitle = "Navigation 3 driven demos for every utility group in the library",
    ) {
        ElevatedCard(
            shape = ShowcaseCardShape,
            colors =
                CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
        ) {
            Column(
                modifier = Modifier.padding(all = 20.dp),
                verticalArrangement = Arrangement.spacedBy(space = 14.dp),
            ) {
                Text(
                    text = "Explore the library by intent",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = "The example app is grouped into layout, adaptive, and theme-focused showcases so the utilities are demonstrated where they make sense in real Compose work.",
                    style = MaterialTheme.typography.bodyLarge,
                )

                MetricGrid(
                    items =
                        listOf(
                            MetricItem(
                                label = "Library version",
                                value = BuildConfig.VERSION_NAME,
                                supporting = "Example app module",
                            ),
                            MetricItem(
                                label = "Theme mode",
                                value = if (isDarkTheme) "Dark" else "Light",
                                supporting =
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                                        "Dynamic color available"
                                    } else {
                                        "Static baseline palette"
                                    },
                            ),
                        ),
                )
            }
        }

        ShowcaseNavigationCard(
            title = "Layout & Insets",
            description = "Density conversion, status/navigation/IME inset helpers, safe padding preview, and PaddingValues operators.",
            utilities =
                "pxToDp • dpToPx • statusBarsPadding • navigationBarsPadding • imePadding • plus • copy • SafePaddingsPreview",
            onClick = onOpenLayoutAndInsets,
        )

        ShowcaseNavigationCard(
            title = "Adaptive & Motion",
            description = "Window size class shortcuts, configuration metrics, legacy screen flags, and animation-scale inspection.",
            utilities =
                "isCompat • isMedium • isExpanded • screenWidthPx • screenHeightPx • isPortrait • isLandscape • getWindowAnimationScale",
            onClick = onOpenAdaptiveAndMotion,
        )

        ShowcaseNavigationCard(
            title = "Theme & Color",
            description = "Material typography, role-based color preview, tonal ramps, and luminance helpers for ColorInt values.",
            utilities =
                "MaterialTypographyPreview • MaterialColorSchemePreview • MaterialColorSchemeTonesPreview • isDarkColor • isLightColor",
            onClick = onOpenThemeAndColor,
        )
    }
}

@Composable
private fun LayoutAndInsetsScreen() {
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    var inputText by rememberSaveable { androidx.compose.runtime.mutableStateOf("") }

    val basePadding = remember { PaddingValues(horizontal = 16.dp, vertical = 12.dp) }
    val screenPadding = remember { PaddingValues(top = 8.dp, bottom = 20.dp) }
    val mergedPadding = basePadding + screenPadding
    val adjustedPadding = mergedPadding.copy(top = 24.dp, end = 28.dp)

    val dp24InPx = with(density) { dpToPx(24.dp) }
    val px96InDp = with(density) { pxToDp(96) }
    val px96fInDp = with(density) { pxToDp(96f) }

    val statusBarsDp = with(density) { statusBarsPadding() }
    val statusBarsPx = with(density) { statusBarsPaddingPx() }
    val navigationBarsDp = with(density) { navigationBarsPadding() }
    val navigationBarsPx = with(density) { navigationBarsPaddingPx() }
    val imeDp = with(density) { imePadding() }
    val imePx = with(density) { imePaddingPx() }

    ShowcaseSection(
        title = "Density conversions",
        description = "The density helpers convert without wrapping every call in a nested `with(density)` block.",
    ) {
        MetricGrid(
            items =
                listOf(
                    MetricItem(
                        label = "Current density",
                        value = density.density.toPrettyString(),
                        supporting = "dp scale factor",
                    ),
                    MetricItem(
                        label = "24.dp -> px",
                        value = dp24InPx.toPrettyString(),
                        supporting = "via Density.dpToPx",
                    ),
                    MetricItem(
                        label = "96px -> dp",
                        value = px96InDp.value.toPrettyString() + " dp",
                        supporting = "via Density.pxToDp(Int)",
                    ),
                    MetricItem(
                        label = "96f px -> dp",
                        value = px96fInDp.value.toPrettyString() + " dp",
                        supporting = "via Density.pxToDp(Float)",
                    ),
                ),
        )
    }

    ShowcaseSection(
        title = "Insets helpers",
        description = "These values update with the current system bars and IME. Focus the field below on a device to watch the keyboard numbers change.",
    ) {
        MetricGrid(
            items =
                listOf(
                    MetricItem(
                        label = "Status bars",
                        value = "${statusBarsDp.value.toPrettyString()} dp",
                        supporting = "$statusBarsPx px",
                    ),
                    MetricItem(
                        label = "Navigation bars",
                        value = "${navigationBarsDp.value.toPrettyString()} dp",
                        supporting = "$navigationBarsPx px",
                    ),
                    MetricItem(
                        label = "IME",
                        value = "${imeDp.value.toPrettyString()} dp",
                        supporting = "$imePx px",
                    ),
                ),
        )

        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Tap to inspect IME padding") },
            modifier = Modifier.fillMaxWidth(),
            supportingText = {
                Text("Use this field to trigger the keyboard and validate the `imePadding()` helpers.")
            },
        )
    }

    ShowcaseSection(
        title = "PaddingValues operators",
        description = "The `plus` and `copy` utilities make it easier to merge screen padding with custom insets and then fine tune individual edges.",
    ) {
        MetricGrid(
            items =
                listOf(
                    MetricItem(
                        label = "Base padding",
                        value = basePadding.describe(layoutDirection = layoutDirection),
                        supporting = "horizontal + vertical seed",
                    ),
                    MetricItem(
                        label = "Screen padding",
                        value = screenPadding.describe(layoutDirection = layoutDirection),
                        supporting = "what a parent container might provide",
                    ),
                    MetricItem(
                        label = "Merged with `plus`",
                        value = mergedPadding.describe(layoutDirection = layoutDirection),
                        supporting = "base + screen",
                    ),
                    MetricItem(
                        label = "Adjusted with `copy`",
                        value = adjustedPadding.describe(layoutDirection = layoutDirection),
                        supporting = "top and end overridden",
                    ),
                ),
        )

        ElevatedCard(
            shape = ShowcaseInnerShape,
            colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceContainer),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(space = 12.dp),
            ) {
                Text(
                    text = "Visual sample",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.secondaryContainer,
                            shape = ShowcaseInnerShape,
                        )
                        .padding(paddingValues = mergedPadding),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.tertiaryContainer,
                                shape = ShowcaseInnerShape,
                            )
                            .padding(paddingValues = adjustedPadding),
                    ) {
                        Text(
                            text = "The outer box uses `plus`, the inner box uses `copy`.",
                            modifier = Modifier.padding(all = 16.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onTertiaryContainer,
                        )
                    }
                }
            }
        }
    }

    ShowcaseSection(
        title = "Safe paddings preview",
        description = "This embeds the dedicated preview composable from the library so you can inspect how Compose safe-area modifiers layer on top of one another.",
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 240.dp)
                .clip(shape = ShowcaseInnerShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant,
                    shape = ShowcaseInnerShape,
                ),
        ) {
            com.jet.utils.SafePaddingsPreview()
        }
    }
}

@Composable
private fun AdaptiveAndMotionScreen() {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSizeClass = adaptiveInfo.windowSizeClass
    val configuration = LocalConfiguration.current
    val context = LocalContext.current
    val animationScale = getWindowAnimationScale()

    val widthSizeClass = windowSizeClass.windowWidthSizeClass
    val heightSizeClass = windowSizeClass.windowHeightSizeClass

    ShowcaseSection(
        title = "Window size class shortcuts",
        description = "The adaptive helpers keep width and height class checks terse when branching layouts.",
    ) {
        MetricGrid(
            items =
                listOf(
                    MetricItem(
                        label = "Width class",
                        value = widthSizeClass.label(),
                        supporting =
                            "isCompat=${widthSizeClass.isCompat} • isMedium=${widthSizeClass.isMedium} • isExpanded=${widthSizeClass.isExpanded}",
                    ),
                    MetricItem(
                        label = "Height class",
                        value = heightSizeClass.label(),
                        supporting =
                            "isCompat=${heightSizeClass.isCompat} • isMedium=${heightSizeClass.isMedium} • isExpanded=${heightSizeClass.isExpanded}",
                    ),
                    MetricItem(
                        label = "Window posture",
                        value = adaptiveInfo.windowPosture.toString(),
                        supporting = "From currentWindowAdaptiveInfo()",
                    ),
                ),
        )
    }

    ShowcaseSection(
        title = "Configuration metrics",
        description = "These values come from the configuration utilities in the library and mirror the current display in both dp and px.",
    ) {
        MetricGrid(
            items =
                listOf(
                    MetricItem(
                        label = "Width (dp)",
                        value = configuration.screenWidthDp.toString(),
                        supporting = "Platform configuration",
                    ),
                    MetricItem(
                        label = "Height (dp)",
                        value = configuration.screenHeightDp.toString(),
                        supporting = "Platform configuration",
                    ),
                    MetricItem(
                        label = "Width (px)",
                        value = configuration.screenWidthPx.toPrettyString(),
                        supporting = "From Configuration.screenWidthPx",
                    ),
                    MetricItem(
                        label = "Height (px)",
                        value = configuration.screenHeightPx.toPrettyString(),
                        supporting = "From Configuration.screenHeightPx",
                    ),
                ),
        )
    }

    ShowcaseSection(
        title = "Legacy configuration flags",
        description = "These helpers are still available in the library for older checks, but they are intentionally marked as deprecated in favor of Material 3 window size classes.",
    ) {
        MetricGrid(
            items =
                listOf(
                    MetricItem(label = "isSmallScreen", value = deprecatedFlag { configuration.isSmallScreen }.toString()),
                    MetricItem(label = "isNormalScreen", value = deprecatedFlag { configuration.isNormalScreen }.toString()),
                    MetricItem(label = "isLargeScreen", value = deprecatedFlag { configuration.isLargeScreen }.toString()),
                    MetricItem(label = "isExtraLargeScreen", value = deprecatedFlag { configuration.isExtraLargeScreen }.toString()),
                    MetricItem(label = "isPortrait", value = deprecatedFlag { configuration.isPortrait }.toString()),
                    MetricItem(label = "isLandScape", value = deprecatedFlag { configuration.isLandScape }.toString()),
                ),
        )
    }

    ShowcaseSection(
        title = "Animation scale",
        description = "This reflects the system animator duration scale from developer options so custom animations can respect the user or debugging environment.",
    ) {
        MetricGrid(
            items =
                listOf(
                    MetricItem(
                        label = "Composable helper",
                        value = animationScale.toPrettyString(),
                        supporting = "getWindowAnimationScale()",
                    ),
                    MetricItem(
                        label = "Context extension",
                        value = context.windowAnimationScale.toPrettyString(),
                        supporting = "Context.windowAnimationScale",
                    ),
                ),
        )
    }
}

@Composable
private fun ThemeAndColorScreen() {
    val colorScheme = MaterialTheme.colorScheme

    ShowcaseSection(
        title = "Typography preview",
        description = "The dedicated typography preview renders every major Material 3 text role with its configured size.",
    ) {
        MaterialTypographyPreview()
    }

    ShowcaseSection(
        title = "Color roles preview",
        description = "This is the role-based Material color sheet from the library, grouped around the same relationships designers work with.",
    ) {
        MaterialColorSchemePreview()
    }

    ShowcaseSection(
        title = "Tonal palettes",
        description = "This tonal-ramp preview mirrors the palette view designers use to inspect how the active theme spreads across Material tone stops.",
    ) {
        MaterialColorSchemeTonesPreview()
    }

    ShowcaseSection(
        title = "ColorInt luminance helpers",
        description = "These checks operate on `@ColorInt Int` values and are handy when you need lightweight readability or theme heuristics outside Compose color roles.",
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        ) {
            listOf(
                "Primary" to colorScheme.primary,
                "Primary Container" to colorScheme.primaryContainer,
                "Surface" to colorScheme.surface,
                "Inverse Surface" to colorScheme.inverseSurface,
            ).forEach { (label, color) ->
                ColorAnalysisCard(
                    label = label,
                    color = color,
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ShowcaseScaffold(
    title: String,
    subtitle: String,
    onBack: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleLarge,
                            fontWeight = FontWeight.SemiBold,
                        )
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                    }
                },
                navigationIcon = {
                    if (onBack != null) {
                        TextButton(onClick = onBack) {
                            Text("Back")
                        }
                    }
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    ),
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState())
                .padding(paddingValues = paddingValues)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(space = 16.dp),
            content = content,
        )
    }
}

@Composable
private fun ShowcaseNavigationCard(
    title: String,
    description: String,
    utilities: String,
    onClick: () -> Unit,
) {
    ElevatedCard(
        shape = ShowcaseCardShape,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier.padding(all = 18.dp),
            verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            HorizontalDivider()
            Text(
                text = utilities,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            FilledTonalButton(
                onClick = onClick,
                modifier = Modifier.widthIn(min = 132.dp),
            ) {
                Text("Open showcase")
            }
        }
    }
}

@Composable
private fun ShowcaseSection(
    title: String,
    description: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    ElevatedCard(
        shape = ShowcaseCardShape,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLow),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 18.dp),
            verticalArrangement = Arrangement.spacedBy(space = 14.dp),
            content = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                content()
            },
        )
    }
}

@Composable
private fun MetricGrid(
    items: List<MetricItem>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(space = 12.dp),
    ) {
        items.chunked(size = 2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space = 12.dp),
            ) {
                rowItems.forEach { item ->
                    MetricCard(
                        item = item,
                        modifier = Modifier.weight(weight = 1f),
                    )
                }
                if (rowItems.size == 1) {
                    Spacer(modifier = Modifier.weight(weight = 1f))
                }
            }
        }
    }
}

@Composable
private fun MetricCard(
    item: MetricItem,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        shape = ShowcaseInnerShape,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp,
    ) {
        Column(
            modifier = Modifier.padding(all = 14.dp),
            verticalArrangement = Arrangement.spacedBy(space = 8.dp),
        ) {
            Text(
                text = item.label,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = item.value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
            )
            item.supporting?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
private fun ColorAnalysisCard(
    label: String,
    color: Color,
) {
    val colorInt = color.toArgb()
    val contentColor = if (colorInt.isProbablyDarkColor) Color.White else Color.Black

    ElevatedCard(
        shape = ShowcaseInnerShape,
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(
            modifier = Modifier.padding(all = 14.dp),
            verticalArrangement = Arrangement.spacedBy(space = 12.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = ShowcaseInnerShape)
                    .background(color = color)
                    .padding(all = 16.dp),
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(space = 6.dp),
                ) {
                    Text(
                        text = label,
                        color = contentColor,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                    )
                    Text(
                        text = colorInt.toHexColor(),
                        color = contentColor,
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
            }

            MetricGrid(
                items =
                    listOf(
                        MetricItem(label = "Probably dark", value = colorInt.isProbablyDarkColor.toString()),
                        MetricItem(label = "Dark", value = colorInt.isDarkColor.toString()),
                        MetricItem(label = "Fully dark", value = colorInt.isFullyDarkColor.toString()),
                        MetricItem(label = "Probably light", value = colorInt.isProbablyLightColor.toString()),
                        MetricItem(label = "Light", value = colorInt.isLightColor.toString()),
                        MetricItem(label = "Fully light", value = colorInt.isFullyLightColor.toString()),
                    ),
            )
        }
    }
}

private fun PaddingValues.describe(
    layoutDirection: LayoutDirection,
): String {
    return listOf(
        "start ${calculateStartPadding(layoutDirection = layoutDirection).value.toPrettyString()}dp",
        "top ${calculateTopPadding().value.toPrettyString()}dp",
        "end ${calculateEndPadding(layoutDirection = layoutDirection).value.toPrettyString()}dp",
        "bottom ${calculateBottomPadding().value.toPrettyString()}dp",
    ).joinToString(separator = " • ")
}

private fun Float.toPrettyString(): String {
    return if (this % 1f == 0f) {
        toInt().toString()
    } else {
        String.format("%.2f", this)
    }
}

private fun Int.toHexColor(): String {
    return String.format("#%08X", this)
}

private fun WindowWidthSizeClass.label(): String {
    return when {
        this.isCompat -> "Compact"
        this.isMedium -> "Medium"
        this.isExpanded -> "Expanded"
        else -> toString()
    }
}

private fun WindowHeightSizeClass.label(): String {
    return when {
        this.isCompat -> "Compact"
        this.isMedium -> "Medium"
        this.isExpanded -> "Expanded"
        else -> toString()
    }
}

@Suppress("DEPRECATION")
private inline fun deprecatedFlag(
    block: () -> Boolean,
): Boolean = block()

private tailrec fun Context.findActivity(): Activity? {
    return when (this) {
        is Activity -> this
        is ContextWrapper -> baseContext.findActivity()
        else -> null
    }
}

private data class MetricItem(
    val label: String,
    val value: String,
    val supporting: String? = null,
)
