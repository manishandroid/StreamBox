package com.imandroid.streambox.core.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * CompositionLocals for StreamBox theme values.
 *
 * These provide the theme values down the composition tree.
 * Access via [AppTheme] object, not directly.
 */
internal val LocalAppColors = staticCompositionLocalOf { DarkColors }
internal val LocalAppTypography = staticCompositionLocalOf { DefaultTypography }
internal val LocalAppSpacers = staticCompositionLocalOf { DefaultSpacers }

/**
 * StreamBox theme accessor object.
 *
 * ## Pattern
 * Provides access to colors, typography, and spacing via Composition Locals.
 *
 * ## Usage
 * ```kotlin
 * @Composable
 * fun MyScreen() {
 *     Box(
 *         modifier = Modifier
 *             .background(AppTheme.colors.background.primary)
 *             .padding(AppTheme.spacers.md)
 *     ) {
 *         Text(
 *             text = "Hello StreamBox",
 *             style = AppTheme.typography.headlineMedium,
 *             color = AppTheme.colors.text.primary
 *         )
 *     }
 * }
 * ```
 *
 * ## Requirements
 * Must be used within a [StreamBoxTheme] composable.
 */
object AppTheme {

    /**
     * Current color palette based on theme.
     */
    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    /**
     * Current typography styles.
     */
    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    /**
     * Current spacing values.
     */
    val spacers: AppSpacers
        @Composable
        @ReadOnlyComposable
        get() = LocalAppSpacers.current
}

/**
 * StreamBox theme provider.
 *
 * Wraps content with the StreamBox design system theme.
 * Should be applied at the root of the app (typically in MainActivity).
 *
 * @param darkTheme Whether to use dark theme. Defaults to system setting.
 * @param content The composable content to theme.
 */
@Composable
fun StreamBoxTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) DarkColors else LightColors

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppTypography provides DefaultTypography,
        LocalAppSpacers provides DefaultSpacers,
        content = content
    )
}
