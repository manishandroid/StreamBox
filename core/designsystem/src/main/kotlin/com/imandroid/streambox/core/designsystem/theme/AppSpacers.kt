package com.imandroid.streambox.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing tokens for StreamBox design system.
 *
 * ## Pattern
 * Consistent spacing creates visual rhythm and hierarchy.
 * Using tokens instead of raw dp values ensures consistency.
 *
 * ## Usage
 * Access via [AppTheme.spacers]:
 * ```kotlin
 * Column(
 *     modifier = Modifier.padding(AppTheme.spacers.md)
 * ) {
 *     Text("Title")
 *     Spacer(modifier = Modifier.height(AppTheme.spacers.sm))
 *     Text("Subtitle")
 * }
 * ```
 *
 * ## Scale
 * Based on 4dp base unit, following common design system patterns:
 * - xs: 4dp  - Tight spacing, icon padding
 * - sm: 8dp  - Small gaps, compact lists
 * - md: 16dp - Default spacing, section gaps
 * - lg: 24dp - Large gaps, section separators
 * - xl: 32dp - Extra large, major sections
 * - xxl: 48dp - Hero spacing
 */
@Immutable
data class AppSpacers(
    val none: Dp,
    val xs: Dp,
    val sm: Dp,
    val md: Dp,
    val lg: Dp,
    val xl: Dp,
    val xxl: Dp,
)

internal val DefaultSpacers = AppSpacers(
    none = 0.dp,
    xs = 4.dp,
    sm = 8.dp,
    md = 16.dp,
    lg = 24.dp,
    xl = 32.dp,
    xxl = 48.dp,
)
