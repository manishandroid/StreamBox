package com.imandroid.streambox.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imandroid.streambox.core.designsystem.theme.AppTheme
import com.imandroid.streambox.core.designsystem.theme.StreamBoxTheme
import com.imandroid.streambox.core.ui.components.LoadingIndicator
import com.imandroid.streambox.features.home.ui.model.HomeContentUi

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onAction = viewModel::onAction,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    state: HomeState,
    onAction: (HomeAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background.primary),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            HomeState.Idle -> HomeIdle(onAction = onAction)
            HomeState.Loading -> LoadingIndicator()
            is HomeState.Content -> HomeContent(items = state.items)
            is HomeState.Error -> HomeError(message = state.message, onAction = onAction)
        }
    }
}

@Composable
private fun HomeIdle(
    onAction: (HomeAction) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "StreamBox",
            style = AppTheme.typography.displayMedium,
            color = AppTheme.colors.text.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.sm))

        Text(
            text = "Ready to load featured content",
            style = AppTheme.typography.bodyLarge,
            color = AppTheme.colors.text.secondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.xl))

        Button(onClick = { onAction(HomeAction.Load) }) {
            Text(text = "Load")
        }
    }
}

@Composable
private fun HomeContent(
    items: List<HomeContentUi>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Featured picks",
            style = AppTheme.typography.displayMedium,
            color = AppTheme.colors.text.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.lg))

        items.forEach { item ->
            Text(
                text = "${item.title} • ${item.year} • ${item.category}",
                style = AppTheme.typography.bodyLarge,
                color = AppTheme.colors.text.secondary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(AppTheme.spacers.sm))
        }
    }
}

@Composable
private fun HomeError(
    message: String,
    onAction: (HomeAction) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Something went wrong",
            style = AppTheme.typography.displayMedium,
            color = AppTheme.colors.text.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.sm))

        Text(
            text = message,
            style = AppTheme.typography.bodyLarge,
            color = AppTheme.colors.text.secondary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.lg))

        Button(onClick = { onAction(HomeAction.Retry) }) {
            Text(text = "Retry")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    StreamBoxTheme {
        HomeScreen(
            state = HomeState.Content(
                listOf(
                    HomeContentUi("Night Signal", "2024", "Sci-Fi"),
                    HomeContentUi("Harborline", "2023", "Drama")
                )
            ),
            onAction = {}
        )
    }
}
