@file:Suppress("INVISIBLE_MEMBER", "INVISIBLE_REFERENCE")

package com.jet.utils.theme

import android.content.res.Configuration
import androidx.annotation.Keep
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.internal.colorUtil.Cam
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils

/**
 * Shared card shape used by both role blocks and tonal swatches.
 */
private val PreviewItemShape = RoundedCornerShape(size = 10.dp)

/**
 * Vertical and horizontal spacing between larger preview sections.
 */
private val PreviewSectionSpacing = 10.dp

/**
 * Tight spacing used between adjacent chips inside the same section.
 */
private val PreviewItemSpacing = 4.dp

/**
 * Material tone stops rendered by the tonal palette preview.
 */
private val ToneStops: List<Int> = listOf(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 95, 98, 99, 100)


/**
 * @param modifier Unused but kept for backward compatibility
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 26.08.2023
 */
@Composable
@Deprecated(message = "Use MaterialColorSchemePreview instead")
public fun MaterialColorsPreview(
    modifier: Modifier = Modifier,
) {
    MaterialColorSchemePreviewLayout(
        colorScheme = MaterialTheme.colorScheme,
        modifier = modifier,
    )
}

/**
 * Shows the Material 3 color roles grouped in the same spirit as the official color system sheet.
 */
@Composable
public fun MaterialColorSchemePreview() {
    MaterialColorSchemePreviewLayout(colorScheme = MaterialTheme.colorScheme)
}

/**
 * Shows tonal ramps derived from the active Material 3 color scheme.
 */
@Composable
public fun MaterialColorSchemeTonesPreview() {
    MaterialColorSchemeTonesPreviewLayout(colorScheme = MaterialTheme.colorScheme)
}

/**
 * Arranges the active [ColorScheme] into grouped Material color-role sections.
 */
@Composable
private fun MaterialColorSchemePreviewLayout(
    colorScheme: ColorScheme,
    modifier: Modifier = Modifier,
) {
    val previewModel = remember(key1 = colorScheme) {
        MaterialColorRolePreviewModel(colorScheme = colorScheme)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalArrangement = Arrangement.spacedBy(space = PreviewSectionSpacing),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = PreviewSectionSpacing),
        ) {
            AccentRoleColumn(
                headline = previewModel.primary,
                modifier = Modifier.weight(weight = 1f),
            )
            AccentRoleColumn(
                headline = previewModel.secondary,
                modifier = Modifier.weight(weight = 1f),
            )
            AccentRoleColumn(
                headline = previewModel.tertiary,
                modifier = Modifier.weight(weight = 1f),
            )
            AccentRoleColumn(
                headline = previewModel.error,
                modifier = Modifier.weight(weight = 1f),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = PreviewSectionSpacing),
        ) {
            FixedRoleColumn(
                roles = previewModel.primaryFixed,
                modifier = Modifier.weight(weight = 1f),
            )
            FixedRoleColumn(
                roles = previewModel.secondaryFixed,
                modifier = Modifier.weight(weight = 1f),
            )
            FixedRoleColumn(
                roles = previewModel.tertiaryFixed,
                modifier = Modifier.weight(weight = 1f),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = PreviewSectionSpacing),
            verticalAlignment = Alignment.Top,
        ) {
            SurfaceRoleGrid(
                roles = previewModel.surfaceRoles,
                modifier = Modifier.weight(weight = 3f),
            )
            SideRoleColumn(
                roles = previewModel.sideRoles,
                modifier = Modifier.weight(weight = 1f),
            )
        }
    }
}

/**
 * Renders the derived tonal ramps for the active [ColorScheme].
 */
@Composable
private fun MaterialColorSchemeTonesPreviewLayout(
    colorScheme: ColorScheme,
    modifier: Modifier = Modifier,
) {
    val palettes = remember(colorScheme) {
        listOf(
            buildTonePalette(name = "Primary", seed = colorScheme.primary),
            buildTonePalette(name = "Secondary", seed = colorScheme.secondary),
            buildTonePalette(name = "Tertiary", seed = colorScheme.tertiary),
            buildTonePalette(name = "Neutral", seed = colorScheme.surface),
            buildTonePalette(name = "Neutral variant", seed = colorScheme.surfaceVariant),
        )
    }

    Box(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .horizontalScroll(state = rememberScrollState())
                .wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(space = PreviewSectionSpacing),
        ) {
            palettes.forEach { palette ->
                TonePaletteRow(palette = palette)
            }
        }
    }
}

/**
 * Displays the primary, secondary, tertiary, or error role stack.
 */
@Composable
private fun AccentRoleColumn(
    headline: AccentRolePreview,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
    ) {
        RoleColorBlock(
            label = headline.main.label,
            color = headline.main.color,
            contentColor = headline.main.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 76.dp),
        )
        RoleColorBlock(
            label = headline.onMain.label,
            color = headline.onMain.color,
            contentColor = headline.onMain.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 66.dp),
        )
        RoleColorBlock(
            label = headline.container.label,
            color = headline.container.color,
            contentColor = headline.container.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 76.dp),
        )
        RoleColorBlock(
            label = headline.onContainer.label,
            color = headline.onContainer.color,
            contentColor = headline.onContainer.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 66.dp),
        )
    }
}

/**
 * Displays a fixed-role section with its paired fixed tones and readable foreground roles.
 */
@Composable
private fun FixedRoleColumn(
    roles: FixedRolePreview,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
        ) {
            RoleColorBlock(
                label = roles.fixed.label,
                color = roles.fixed.color,
                contentColor = roles.fixed.contentColor,
                modifier = Modifier
                    .weight(weight = 1f)
                    .heightIn(min = 84.dp),
            )
            RoleColorBlock(
                label = roles.fixedDim.label,
                color = roles.fixedDim.color,
                contentColor = roles.fixedDim.contentColor,
                modifier = Modifier
                    .weight(weight = 1f)
                    .heightIn(min = 84.dp),
            )
        }

        RoleColorBlock(
            label = roles.onFixed.label,
            color = roles.onFixed.color,
            contentColor = roles.onFixed.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 66.dp),
        )
        RoleColorBlock(
            label = roles.onFixedVariant.label,
            color = roles.onFixedVariant.color,
            contentColor = roles.onFixedVariant.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 66.dp),
        )
    }
}

/**
 * Displays the surface family together with outline and on-surface support roles.
 */
@Composable
private fun SurfaceRoleGrid(
    roles: SurfaceRolePreview,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
        ) {
            RoleColorBlock(
                label = roles.surfaceDim.label,
                color = roles.surfaceDim.color,
                contentColor = roles.surfaceDim.contentColor,
                modifier = Modifier
                    .weight(weight = 1f)
                    .heightIn(min = 72.dp),
            )
            RoleColorBlock(
                label = roles.surface.label,
                color = roles.surface.color,
                contentColor = roles.surface.contentColor,
                modifier = Modifier
                    .weight(weight = 2f)
                    .heightIn(min = 72.dp),
            )
            RoleColorBlock(
                label = roles.surfaceBright.label,
                color = roles.surfaceBright.color,
                contentColor = roles.surfaceBright.contentColor,
                modifier = Modifier
                    .weight(weight = 1f)
                    .heightIn(min = 72.dp),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
        ) {
            listOf(
                roles.surfaceContainerLowest,
                roles.surfaceContainerLow,
                roles.surfaceContainer,
                roles.surfaceContainerHigh,
                roles.surfaceContainerHighest,
            ).forEach { role ->
                RoleColorBlock(
                    label = role.label,
                    color = role.color,
                    contentColor = role.contentColor,
                    modifier = Modifier
                        .weight(weight = 1f)
                        .heightIn(min = 84.dp),
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
        ) {
            listOf(
                roles.onSurface,
                roles.onSurfaceVariant,
                roles.outline,
                roles.outlineVariant,
            ).forEach { role ->
                RoleColorBlock(
                    label = role.label,
                    color = role.color,
                    contentColor = role.contentColor,
                    modifier = Modifier
                        .weight(weight = 1f)
                        .heightIn(min = 58.dp),
                )
            }
        }
    }
}

/**
 * Displays inverse and utility roles that sit beside the main surface grid.
 */
@Composable
private fun SideRoleColumn(
    roles: SideRolePreview,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
    ) {
        RoleColorBlock(
            label = roles.inverseSurface.label,
            color = roles.inverseSurface.color,
            contentColor = roles.inverseSurface.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 54.dp),
        )
        RoleColorBlock(
            label = roles.inverseOnSurface.label,
            color = roles.inverseOnSurface.color,
            contentColor = roles.inverseOnSurface.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 54.dp),
        )
        RoleColorBlock(
            label = roles.inversePrimary.label,
            color = roles.inversePrimary.color,
            contentColor = roles.inversePrimary.contentColor,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 54.dp),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
        ) {
            RoleColorBlock(
                label = roles.scrim.label,
                color = roles.scrim.color,
                contentColor = roles.scrim.contentColor,
                modifier = Modifier
                    .weight(weight = 1f)
                    .heightIn(min = 54.dp),
            )
            RoleColorBlock(
                label = roles.surfaceTint.label,
                color = roles.surfaceTint.color,
                contentColor = roles.surfaceTint.contentColor,
                modifier = Modifier
                    .weight(weight = 1f)
                    .heightIn(min = 54.dp),
            )
        }
    }
}

/**
 * Displays one full tonal ramp row with a left-aligned palette name.
 */
@Composable
private fun TonePaletteRow(
    palette: TonePalettePreview,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space = PreviewSectionSpacing),
    ) {
        Box(
            modifier = Modifier.width(width = 132.dp),
            contentAlignment = Alignment.CenterStart,
        ) {
            androidx.compose.material3.Text(
                text = palette.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(space = PreviewItemSpacing),
        ) {
            palette.swatches.forEach { swatch ->
                ToneColorBlock(
                    swatch = swatch,
                    modifier = Modifier
                        .widthIn(min = 56.dp)
                        .height(height = 70.dp),
                )
            }
        }
    }
}

/**
 * Draws a labeled color block for a Material role.
 */
@Composable
private fun RoleColorBlock(
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
    contentColor: Color? = null,
) {
    val resolvedColor = color.takeOrElse { MaterialTheme.colorScheme.surface }
    val resolvedContentColor =
        contentColor?.takeOrElse { resolvedColor.bestTextColor() } ?: resolvedColor.bestTextColor()
    val borderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.7f)

    Box(
        modifier = modifier
            .background(color = resolvedColor, shape = PreviewItemShape)
            .border(width = 1.dp, color = borderColor, shape = PreviewItemShape)
            .padding(all = 14.dp),
        contentAlignment = Alignment.TopStart,
    ) {
        androidx.compose.material3.Text(
            text = label,
            color = resolvedContentColor,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

/**
 * Draws a single tone swatch with the tone number centered on top.
 */
@Composable
private fun ToneColorBlock(
    swatch: ToneSwatch,
    modifier: Modifier = Modifier,
) {
    val borderColor = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.7f)

    Box(
        modifier = modifier
            .background(color = swatch.color, shape = PreviewItemShape)
            .border(width = 1.dp, color = borderColor, shape = PreviewItemShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = swatch.tone.toString(),
            color = swatch.color.bestTextColor(),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

/**
 * Builds one tonal palette by keeping the seed hue and chroma and sampling Material tone stops.
 */
private fun buildTonePalette(
    name: String,
    seed: Color,
): TonePalettePreview {
    val seedColor = seed.takeOrElse { Color.Gray }
    val seedCam = Cam.fromInt(seedColor.toArgb())
    val swatches = ToneStops.map { tone ->
        ToneSwatch(
            tone = tone,
            color = Color(Cam.getInt(seedCam.hue, seedCam.chroma, tone.toFloat())),
        )
    }

    return TonePalettePreview(
        name = name,
        swatches = swatches,
    )
}

/**
 * Converts a [ColorScheme] into a UI-oriented model that is easier for the preview layouts to render.
 */
private fun MaterialColorRolePreviewModel(
    colorScheme: ColorScheme,
): MaterialColorRolePreviewModel {
    val primaryFixed = colorScheme.primaryFixed.orElse(fallback = colorScheme.primaryContainer)
    val primaryFixedDim = colorScheme.primaryFixedDim.orElse(fallback = colorScheme.inversePrimary)
    val onPrimaryFixed =
        colorScheme.onPrimaryFixed.orElse(fallback = colorScheme.onPrimaryContainer)
    val onPrimaryFixedVariant =
        colorScheme.onPrimaryFixedVariant.orElse(fallback = colorScheme.primary)

    val secondaryFixed =
        colorScheme.secondaryFixed.orElse(fallback = colorScheme.secondaryContainer)
    val secondaryFixedDim = colorScheme.secondaryFixedDim.orElse(fallback = colorScheme.secondary)
    val onSecondaryFixed =
        colorScheme.onSecondaryFixed.orElse(fallback = colorScheme.onSecondaryContainer)
    val onSecondaryFixedVariant =
        colorScheme.onSecondaryFixedVariant.orElse(fallback = colorScheme.secondary)

    val tertiaryFixed = colorScheme.tertiaryFixed.orElse(fallback = colorScheme.tertiaryContainer)
    val tertiaryFixedDim = colorScheme.tertiaryFixedDim.orElse(fallback = colorScheme.tertiary)
    val onTertiaryFixed =
        colorScheme.onTertiaryFixed.orElse(fallback = colorScheme.onTertiaryContainer)
    val onTertiaryFixedVariant =
        colorScheme.onTertiaryFixedVariant.orElse(fallback = colorScheme.tertiary)

    val surfaceDim = colorScheme.surfaceDim.orElse(fallback = colorScheme.surface)
    val surfaceBright = colorScheme.surfaceBright.orElse(fallback = colorScheme.surface)
    val surfaceContainerLowest =
        colorScheme.surfaceContainerLowest.orElse(fallback = colorScheme.surface)
    val surfaceContainerLow = colorScheme.surfaceContainerLow.orElse(fallback = colorScheme.surface)
    val surfaceContainer = colorScheme.surfaceContainer.orElse(fallback = colorScheme.surface)
    val surfaceContainerHigh =
        colorScheme.surfaceContainerHigh.orElse(fallback = colorScheme.surfaceVariant)
    val surfaceContainerHighest =
        colorScheme.surfaceContainerHighest.orElse(fallback = colorScheme.surfaceVariant)

    return MaterialColorRolePreviewModel(
        primary = accentPreview(
            mainLabel = "Primary",
            mainColor = colorScheme.primary,
            onMainLabel = "On Primary",
            onMainColor = colorScheme.onPrimary,
            containerLabel = "Primary Container",
            containerColor = colorScheme.primaryContainer,
            onContainerLabel = "On Primary Container",
            onContainerColor = colorScheme.onPrimaryContainer,
        ),
        secondary = accentPreview(
            mainLabel = "Secondary",
            mainColor = colorScheme.secondary,
            onMainLabel = "On Secondary",
            onMainColor = colorScheme.onSecondary,
            containerLabel = "Secondary Container",
            containerColor = colorScheme.secondaryContainer,
            onContainerLabel = "On Secondary Container",
            onContainerColor = colorScheme.onSecondaryContainer,
        ),
        tertiary = accentPreview(
            mainLabel = "Tertiary",
            mainColor = colorScheme.tertiary,
            onMainLabel = "On Tertiary",
            onMainColor = colorScheme.onTertiary,
            containerLabel = "Tertiary Container",
            containerColor = colorScheme.tertiaryContainer,
            onContainerLabel = "On Tertiary Container",
            onContainerColor = colorScheme.onTertiaryContainer,
        ),
        error = accentPreview(
            mainLabel = "Error",
            mainColor = colorScheme.error,
            onMainLabel = "On Error",
            onMainColor = colorScheme.onError,
            containerLabel = "Error Container",
            containerColor = colorScheme.errorContainer,
            onContainerLabel = "On Error Container",
            onContainerColor = colorScheme.onErrorContainer,
        ),
        primaryFixed = fixedPreview(
            prefix = "Primary",
            fixed = primaryFixed,
            fixedDim = primaryFixedDim,
            onFixed = onPrimaryFixed,
            onFixedVariant = onPrimaryFixedVariant,
        ),
        secondaryFixed = fixedPreview(
            prefix = "Secondary",
            fixed = secondaryFixed,
            fixedDim = secondaryFixedDim,
            onFixed = onSecondaryFixed,
            onFixedVariant = onSecondaryFixedVariant,
        ),
        tertiaryFixed = fixedPreview(
            prefix = "Tertiary",
            fixed = tertiaryFixed,
            fixedDim = tertiaryFixedDim,
            onFixed = onTertiaryFixed,
            onFixedVariant = onTertiaryFixedVariant,
        ),
        surfaceRoles = SurfaceRolePreview(
            surfaceDim = role(
                label = "Surface Dim",
                color = surfaceDim,
                contentColor = colorScheme.onSurface
            ),
            surface = role(
                label = "Surface",
                color = colorScheme.surface,
                contentColor = colorScheme.onSurface
            ),
            surfaceBright = role(
                label = "Surface Bright",
                color = surfaceBright,
                contentColor = colorScheme.onSurface
            ),
            surfaceContainerLowest = role(
                label = "Surface Container Lowest",
                color = surfaceContainerLowest,
                contentColor = colorScheme.onSurface,
            ),
            surfaceContainerLow = role(
                label = "Surface Container Low",
                color = surfaceContainerLow,
                contentColor = colorScheme.onSurface,
            ),
            surfaceContainer = role(
                label = "Surface Container",
                color = surfaceContainer,
                contentColor = colorScheme.onSurface,
            ),
            surfaceContainerHigh = role(
                label = "Surface Container High",
                color = surfaceContainerHigh,
                contentColor = colorScheme.onSurface,
            ),
            surfaceContainerHighest = role(
                label = "Surface Container Highest",
                color = surfaceContainerHighest,
                contentColor = colorScheme.onSurface,
            ),
            onSurface = role(label = "On Surface", color = colorScheme.onSurface),
            onSurfaceVariant = role(
                label = "On Surface Variant",
                color = colorScheme.onSurfaceVariant
            ),
            outline = role(label = "Outline", color = colorScheme.outline),
            outlineVariant = role(label = "Outline Variant", color = colorScheme.outlineVariant),
        ),
        sideRoles = SideRolePreview(
            inverseSurface = role(
                label = "Inverse Surface",
                color = colorScheme.inverseSurface,
                contentColor = colorScheme.inverseOnSurface,
            ),
            inverseOnSurface = role(
                label = "Inverse On Surface",
                color = colorScheme.inverseOnSurface,
                contentColor = colorScheme.inverseSurface,
            ),
            inversePrimary = role(label = "Inverse Primary", color = colorScheme.inversePrimary),
            scrim = role(label = "Scrim", color = colorScheme.scrim),
            surfaceTint = role(
                label = "Surface Tint",
                color = colorScheme.surfaceTint.orElse(fallback = colorScheme.primary)
            ),
        ),
    )
}

/**
 * Creates the preview model for one accent role family.
 */
private fun accentPreview(
    mainLabel: String,
    mainColor: Color,
    onMainLabel: String,
    onMainColor: Color,
    containerLabel: String,
    containerColor: Color,
    onContainerLabel: String,
    onContainerColor: Color,
): AccentRolePreview {
    return AccentRolePreview(
        main = role(label = mainLabel, color = mainColor, contentColor = onMainColor),
        onMain = role(label = onMainLabel, color = onMainColor, contentColor = mainColor),
        container = role(
            label = containerLabel,
            color = containerColor,
            contentColor = onContainerColor
        ),
        onContainer = role(
            label = onContainerLabel,
            color = onContainerColor,
            contentColor = containerColor
        ),
    )
}

/**
 * Creates the preview model for one fixed-color family.
 */
private fun fixedPreview(
    prefix: String,
    fixed: Color,
    fixedDim: Color,
    onFixed: Color,
    onFixedVariant: Color,
): FixedRolePreview {
    return FixedRolePreview(
        fixed = role(label = "$prefix Fixed", color = fixed, contentColor = onFixed),
        fixedDim = role(label = "$prefix Fixed Dim", color = fixedDim, contentColor = onFixed),
        onFixed = role(label = "On $prefix Fixed", color = onFixed, contentColor = fixed),
        onFixedVariant = role(
            label = "On $prefix Fixed Variant",
            color = onFixedVariant,
            contentColor = fixed,
        ),
    )
}

/**
 * Wraps a labeled preview color and its preferred content color.
 */
private fun role(
    label: String,
    color: Color,
    contentColor: Color? = null,
): ColorRolePreview {
    return ColorRolePreview(
        label = label,
        color = color,
        contentColor = contentColor,
    )
}

/**
 * Picks black or white text based on the stronger contrast ratio against this background color.
 */
private fun Color.bestTextColor(): Color {
    val background = this.toArgb()
    val blackContrast = ColorUtils.calculateContrast(Color.Black.toArgb(), background)
    val whiteContrast = ColorUtils.calculateContrast(Color.White.toArgb(), background)

    return if (whiteContrast >= blackContrast) Color.White else Color.Black
}

/**
 * Resolves [Color.Unspecified] to a provided fallback color.
 */
private fun Color.orElse(
    fallback: Color,
): Color {
    return this.takeOrElse { fallback }
}

/**
 * Aggregates all sections required by the full color-role preview.
 */
@Keep
@Immutable
private data class MaterialColorRolePreviewModel(
    val primary: AccentRolePreview,
    val secondary: AccentRolePreview,
    val tertiary: AccentRolePreview,
    val error: AccentRolePreview,
    val primaryFixed: FixedRolePreview,
    val secondaryFixed: FixedRolePreview,
    val tertiaryFixed: FixedRolePreview,
    val surfaceRoles: SurfaceRolePreview,
    val sideRoles: SideRolePreview,
)

/**
 * Describes one accent role stack made of main, on-main, container, and on-container colors.
 */
@Keep
@Immutable
private data class AccentRolePreview(
    val main: ColorRolePreview,
    val onMain: ColorRolePreview,
    val container: ColorRolePreview,
    val onContainer: ColorRolePreview,
)

/**
 * Describes one fixed-color family and its matching foreground roles.
 */
@Keep
@Immutable
private data class FixedRolePreview(
    val fixed: ColorRolePreview,
    val fixedDim: ColorRolePreview,
    val onFixed: ColorRolePreview,
    val onFixedVariant: ColorRolePreview,
)

/**
 * Groups surface-related roles that are rendered together in the lower main grid.
 */
@Keep
@Immutable
private data class SurfaceRolePreview(
    val surfaceDim: ColorRolePreview,
    val surface: ColorRolePreview,
    val surfaceBright: ColorRolePreview,
    val surfaceContainerLowest: ColorRolePreview,
    val surfaceContainerLow: ColorRolePreview,
    val surfaceContainer: ColorRolePreview,
    val surfaceContainerHigh: ColorRolePreview,
    val surfaceContainerHighest: ColorRolePreview,
    val onSurface: ColorRolePreview,
    val onSurfaceVariant: ColorRolePreview,
    val outline: ColorRolePreview,
    val outlineVariant: ColorRolePreview,
)

/**
 * Groups inverse and utility roles rendered in the side column.
 */
@Keep
@Immutable
private data class SideRolePreview(
    val inverseSurface: ColorRolePreview,
    val inverseOnSurface: ColorRolePreview,
    val inversePrimary: ColorRolePreview,
    val scrim: ColorRolePreview,
    val surfaceTint: ColorRolePreview,
)

/**
 * Represents one labeled Material role preview block.
 */
@Keep
@Immutable
private data class ColorRolePreview(
    val label: String,
    val color: Color,
    val contentColor: Color? = null,
)

/**
 * Represents one named tonal palette row.
 */
@Keep
@Immutable
private data class TonePalettePreview(
    val name: String,
    val swatches: List<ToneSwatch>,
)

/**
 * Represents a single tone stop inside a tonal palette row.
 */
@Keep
@Immutable
private data class ToneSwatch(
    val tone: Int,
    val color: Color,
)

/**
 * Preview for the color-role sheet in both light and dark modes.
 */
@Composable
@Preview(
    name = "Color Roles Light",
    widthDp = 1280,
    heightDp = 860,
    showBackground = true,
)
@Preview(
    name = "Color Roles Dark",
    widthDp = 1280,
    heightDp = 860,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
private fun MaterialColorSchemePreviewPreview() {
    val colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        Surface(color = MaterialTheme.colorScheme.background) {
            MaterialColorSchemePreview()
        }
    }
}

/**
 * Preview for the tonal palette sheet in both light and dark modes.
 */
@Composable
@Preview(
    name = "Color Tones Light",
    widthDp = 1280,
    heightDp = 520,
    showBackground = true,
)
@Preview(
    name = "Color Tones Dark",
    widthDp = 1280,
    heightDp = 520,
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
private fun MaterialColorSchemeTonesPreviewPreview() {
    val colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()

    MaterialTheme(colorScheme = colorScheme) {
        Surface(color = MaterialTheme.colorScheme.background) {
            MaterialColorSchemeTonesPreview()
        }
    }
}
