package com.imandroid.streambox.features.home.snapshot

import com.imandroid.streambox.features.home.ui.HomeScreen
import com.imandroid.streambox.features.home.ui.HomeState
import com.imandroid.streambox.features.home.ui.model.HomeContentUi
import org.junit.Test

class TopBarTabsSnapshotTest(
    darkTheme: Boolean
) : LightDarkSnapshotTest(darkTheme) {

    @Test
    fun top_bar_and_tabs() {
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
        HomeContentUi("Blue Echo", "2022", "Drama", imageUrl = null)
    )
}
