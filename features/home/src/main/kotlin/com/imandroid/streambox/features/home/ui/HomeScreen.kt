package com.imandroid.streambox.features.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import com.imandroid.streambox.core.designsystem.theme.AppTheme
import com.imandroid.streambox.core.designsystem.theme.StreamBoxTheme
import com.imandroid.streambox.core.ui.components.LoadingIndicator
import com.imandroid.streambox.core.ui.components.TwoByThreeTile
import com.imandroid.streambox.features.home.R
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
            is HomeState.Content -> HomeContentScreen(items = state.items)
            is HomeState.Error -> HomeError(message = state.message, onAction = onAction)
        }
    }
}

@Composable
private fun HomeIdle(
    onAction: (HomeAction) -> Unit
) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .padding(horizontal = AppTheme.spacers.lg, vertical = AppTheme.spacers.lg)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "StreamBox",
            style = AppTheme.typography.displayMedium,
            color = AppTheme.colors.text.primary,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.sm))

        Text(
            text = "A learning-focused Android app that showcases production-grade architecture patterns in a real streaming-style UI.",
            style = AppTheme.typography.bodyLarge,
            color = AppTheme.colors.text.secondary,
            textAlign = TextAlign.Start
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.lg))

        SectionTitle(title = "What it demonstrates")
        SectionBody(
            text = "• Unidirectional data flow\n• Reducers and state-driven UI\n• Use cases, repositories, and DI\n• Offline-ready layering and UI patterns"
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.lg))

        SectionTitle(title = "Why it exists")
        SectionBody(
            text = "StreamBox is built incrementally by branch, so each pattern is isolated and easy to study without noise."
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.lg))

        SectionTitle(title = "Where to explore")
        LinkText(
            label = "Repository",
            url = "https://github.com/manishandroid/StreamBox/",
            onClick = { uriHandler.openUri("https://github.com/manishandroid/StreamBox/") }
        )
        Spacer(modifier = Modifier.height(AppTheme.spacers.xs))
        LinkText(
            label = "Blog",
            url = "https://medium.com/@mandroidubey/the-most-boring-android-app-ive-ever-built-and-why-it-matters-8c0ec50ab601",
            onClick = {
                uriHandler.openUri(
                    "https://medium.com/@mandroidubey/the-most-boring-android-app-ive-ever-built-and-why-it-matters-8c0ec50ab601"
                )
            }
        )

        Spacer(modifier = Modifier.height(AppTheme.spacers.xl))

        Button(
            onClick = { onAction(HomeAction.Load) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Explore App")
        }
    }
}

@Composable
private fun SectionTitle(
    title: String
) {
    Text(
        text = title,
        style = AppTheme.typography.titleMedium,
        color = AppTheme.colors.text.primary
    )
    Spacer(modifier = Modifier.height(AppTheme.spacers.xs))
}

@Composable
private fun SectionBody(
    text: String
) {
    Text(
        text = text,
        style = AppTheme.typography.bodyMedium,
        color = AppTheme.colors.text.secondary
    )
}

@Composable
private fun LinkText(
    label: String,
    url: String,
    onClick: () -> Unit
) {
    val annotated = buildAnnotatedString {
        append("$label: ")
        withStyle(SpanStyle(color = AppTheme.colors.brand.primary)) {
            append(url)
        }
    }

    Text(
        text = annotated,
        style = AppTheme.typography.bodyMedium.copy(color = AppTheme.colors.text.secondary),
        modifier = Modifier
            .padding(vertical = AppTheme.spacers.xs)
            .clickable(onClick = onClick)
    )
}

@Composable
private fun HomeContentScreen(
    items: List<HomeContentUi>
) {
    val genres = remember(items) {
        items.map { it.category }.distinct()
    }
    val selectedGenre = remember(genres) {
        mutableStateOf(genres.firstOrNull().orEmpty())
    }
    val selectedBottomTab = remember { mutableStateOf(BottomNavItem.Home) }
    val selectedIndex = remember(genres, selectedGenre.value) {
        genres.indexOf(selectedGenre.value).coerceAtLeast(0)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopBar()
        GenreTabRow(
            genres = genres,
            selectedIndex = selectedIndex,
            onTabSelected = { index ->
                selectedGenre.value = genres.getOrNull(index).orEmpty()
            }
        )
        HomeGrid(
            items = items.filter { it.category == selectedGenre.value },
            modifier = Modifier.weight(1f)
        )
        HomeBottomBar(
            selected = selectedBottomTab.value,
            onSelected = { selectedBottomTab.value = it }
        )
    }
}

@Composable
private fun HomeTopBar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(AppTheme.colors.background.primary)
            .statusBarsPadding()
            .padding(
                horizontal = AppTheme.spacers.md,
                vertical = AppTheme.spacers.sm
            )
    ) {
        Text(
            text = "StreamBox",
            style = AppTheme.typography.headlineSmall,
            color = AppTheme.colors.text.primary
        )
    }
}

@Composable
private fun GenreTabRow(
    genres: List<String>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    PrimaryScrollableTabRow(
        selectedTabIndex = selectedIndex.coerceAtLeast(0),
        containerColor = AppTheme.colors.background.primary,
        contentColor = AppTheme.colors.text.primary,
        edgePadding = AppTheme.spacers.md,
        divider = {}
    ) {
        genres.forEachIndexed { index, genre ->
            val isSelected = index == selectedIndex
            Tab(
                selected = isSelected,
                onClick = { onTabSelected(index) },
                selectedContentColor = AppTheme.colors.text.primary,
                unselectedContentColor = AppTheme.colors.text.secondary,
                modifier = Modifier
                    .padding(horizontal = AppTheme.spacers.xs, vertical = AppTheme.spacers.sm)
            ) {
                Text(
                    text = genre,
                    style = AppTheme.typography.titleMedium,
                    color = if (isSelected) AppTheme.colors.text.primary else AppTheme.colors.text.secondary,
                    modifier = Modifier.padding(horizontal = AppTheme.spacers.md, vertical = AppTheme.spacers.xs)
                )
            }
        }
    }
}

@Composable
private fun HomeGrid(
    items: List<HomeContentUi>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            horizontal = AppTheme.spacers.md,
            vertical = AppTheme.spacers.lg
        ),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.spacers.md),
        verticalArrangement = Arrangement.spacedBy(AppTheme.spacers.md)
    ) {
        items(items) { item ->
            TwoByThreeTile(
                title = item.title,
                imageUrl = item.imageUrl,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private enum class BottomNavItem(val label: String, val iconRes: Int) {
    Home("Home", R.drawable.ic_nav_home),
    Favorites("My Fav", R.drawable.ic_nav_fav),
    Search("Search", R.drawable.ic_nav_search),
    AppInfo("App Info", R.drawable.ic_nav_info)
}

@Composable
private fun HomeBottomBar(
    selected: BottomNavItem,
    onSelected: (BottomNavItem) -> Unit
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .height(72.dp),
        containerColor = AppTheme.colors.background.primary
    ) {
        BottomNavItem.entries.forEach { item ->
            val isSelected = item == selected
            NavigationBarItem(
                selected = isSelected,
                onClick = { onSelected(item) },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                    selectedIconColor = AppTheme.colors.text.primary,
                    unselectedIconColor = AppTheme.colors.text.secondary,
                    indicatorColor = AppTheme.colors.background.primary
                )
            )
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
                    HomeContentUi("Night Signal", "2024", "Sci-Fi", imageUrl = null),
                    HomeContentUi("Harborline", "2023", "Drama", imageUrl = null)
                )
            ),
            onAction = {}
        )
    }
}
