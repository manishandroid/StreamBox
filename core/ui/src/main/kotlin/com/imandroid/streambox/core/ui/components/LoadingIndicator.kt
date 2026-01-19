package com.imandroid.streambox.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.imandroid.streambox.core.designsystem.theme.AppTheme
import com.imandroid.streambox.core.designsystem.theme.StreamBoxTheme

/**
 * Standard loading indicator for StreamBox.
 *
 * ## Usage
 * ```kotlin
 * when (state) {
 *     is ScreenState.Loading -> LoadingIndicator()
 *     is ScreenState.Content -> ContentScreen(state.data)
 * }
 * ```
 *
 * @param modifier Modifier for the container Box
 */
@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp),
            color = AppTheme.colors.brand.primary,
            trackColor = AppTheme.colors.background.tertiary,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0D0D0D)
@Composable
private fun LoadingIndicatorPreview() {
    StreamBoxTheme {
        LoadingIndicator(
            modifier = Modifier.size(100.dp)
        )
    }
}
