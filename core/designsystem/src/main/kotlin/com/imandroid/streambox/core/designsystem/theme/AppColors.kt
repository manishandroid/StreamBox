package com.imandroid.streambox.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Color tokens for StreamBox design system.
 *
 * ## Pattern
 * Colors are organized into semantic groups (background, text, etc.)
 * rather than raw color values. This enables theming and ensures
 * consistent color usage across the app.
 *
 * ## Usage
 * Access via [AppTheme.colors]:
 * ```kotlin
 * Box(
 *     modifier = Modifier.background(AppTheme.colors.background.primary)
 * ) {
 *     Text(
 *         text = "Hello",
 *         color = AppTheme.colors.text.primary
 *     )
 * }
 * ```
 */
@Immutable
data class AppColors(
    val background: BackgroundColors,
    val text: TextColors,
    val brand: BrandColors,
    val status: StatusColors,
)

@Immutable
data class BackgroundColors(
    val primary: Color,
    val secondary: Color,
    val tertiary: Color,
)

@Immutable
data class TextColors(
    val primary: Color,
    val secondary: Color,
    val disabled: Color,
    val inverse: Color,
)

@Immutable
data class BrandColors(
    val primary: Color,
    val secondary: Color,
)

@Immutable
data class StatusColors(
    val error: Color,
    val success: Color,
    val warning: Color,
)

/**
 * Dark theme colors - primary theme for StreamBox.
 */
internal val DarkColors = AppColors(
    background = BackgroundColors(
        primary = Color(0xFF0D0D0D),
        secondary = Color(0xFF1A1A1A),
        tertiary = Color(0xFF262626),
    ),
    text = TextColors(
        primary = Color(0xFFFFFFFF),
        secondary = Color(0xFFB3B3B3),
        disabled = Color(0xFF666666),
        inverse = Color(0xFF0D0D0D),
    ),
    brand = BrandColors(
        primary = Color(0xFF6366F1),    // Indigo
        secondary = Color(0xFF818CF8),
    ),
    status = StatusColors(
        error = Color(0xFFEF4444),
        success = Color(0xFF22C55E),
        warning = Color(0xFFF59E0B),
    ),
)

/**
 * Light theme colors - available for future use.
 */
internal val LightColors = AppColors(
    background = BackgroundColors(
        primary = Color(0xFFFAFAFA),
        secondary = Color(0xFFF5F5F5),
        tertiary = Color(0xFFE5E5E5),
    ),
    text = TextColors(
        primary = Color(0xFF171717),
        secondary = Color(0xFF525252),
        disabled = Color(0xFFA3A3A3),
        inverse = Color(0xFFFFFFFF),
    ),
    brand = BrandColors(
        primary = Color(0xFF4F46E5),
        secondary = Color(0xFF6366F1),
    ),
    status = StatusColors(
        error = Color(0xFFDC2626),
        success = Color(0xFF16A34A),
        warning = Color(0xFFD97706),
    ),
)
