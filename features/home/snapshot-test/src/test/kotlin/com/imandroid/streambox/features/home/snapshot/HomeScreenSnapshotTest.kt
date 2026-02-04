package com.imandroid.streambox.features.home.snapshot

import com.imandroid.streambox.features.home.ui.HomeScreen
import com.imandroid.streambox.features.home.ui.HomeState
import com.imandroid.streambox.features.home.ui.model.HomeContentUi
import org.junit.Test

class HomeScreenSnapshotTest(
    darkTheme: Boolean
) : LightDarkSnapshotTest(darkTheme) {

    @Test
    fun home_idle() {
        snapshot {
            HomeScreen(
                state = HomeState.Idle,
                onAction = {}
            )
        }
    }

    @Test
    fun home_loading() {
        snapshot {
            HomeScreen(
                state = HomeState.Loading,
                onAction = {}
            )
        }
    }

    @Test
    fun home_error() {
        snapshot {
            HomeScreen(
                state = HomeState.Error("Unable to load content"),
                onAction = {}
            )
        }
    }

    @Test
    fun home_content() {
        snapshot {
            HomeScreen(
                state = HomeState.Content(sampleItems()),
                onAction = {}
            )
        }
    }

    private fun sampleItems(): List<HomeContentUi> = listOf(
        HomeContentUi("Night Signal", "2024", "Drama", imageUrl = null),
        HomeContentUi("Harborline", "2023", "Drama", imageUrl = null),
        HomeContentUi("Blue Echo", "2022", "Drama", imageUrl = null),
        HomeContentUi("Cold Harbor", "2021", "Drama", imageUrl = null)
    )
}
