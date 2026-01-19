package com.imandroid.streambox.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.imandroid.streambox.core.designsystem.theme.AppTheme
import com.imandroid.streambox.core.designsystem.theme.StreamBoxTheme

/**
 * Home screen placeholder for StreamBox.
 *
 * ## Current State (main branch)
 * This is a static placeholder screen that proves the app compiles and runs.
 * It demonstrates the design system integration with AppTheme.
 *
 * ## Future Development
 * In feature branches, this will evolve to:
 * - `feature/reducer-basics`: Add state management with reducer
 * - `feature/network-integration`: Display data from API
 * - `feature/content-list`: Full content browsing experience
 *
 * @param modifier Modifier for the screen container
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background.primary),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App title
            Text(
                text = "StreamBox",
                style = AppTheme.typography.displayMedium,
                color = AppTheme.colors.text.primary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(AppTheme.spacers.sm))

            // Subtitle
            Text(
                text = "Production Pattern Learning App",
                style = AppTheme.typography.bodyLarge,
                color = AppTheme.colors.text.secondary,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(AppTheme.spacers.xl))

            // Branch indicator
            Text(
                text = "Branch: main",
                style = AppTheme.typography.labelMedium,
                color = AppTheme.colors.brand.primary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    StreamBoxTheme {
        HomeScreen()
    }
}
