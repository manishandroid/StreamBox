package com.imandroid.streambox.features.home.ui

import com.imandroid.streambox.core.testing.TestDispatcherProvider
import com.imandroid.streambox.features.home.ui.model.HomeContentUi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeReducerTest {

    private lateinit var reducer: HomeReducer

    @Before
    fun setup() {
        reducer = HomeReducer(TestDispatcherProvider())
    }

    @Test
    fun `given Load when reducer updates then Loading state`() = runTest {
        reducer.update(HomeAction.Load)

        assertEquals(HomeState.Loading, reducer.state.value)
    }

    @Test
    fun `given ContentLoaded when reducer updates then Content state`() = runTest {
        val items = listOf(
            HomeContentUi(title = "Night Signal", year = "2024", category = "Sci-Fi")
        )

        reducer.update(HomeAction.ContentLoaded(items))

        assertEquals(HomeState.Content(items), reducer.state.value)
    }

    @Test
    fun `given LoadingFailed when reducer updates then Error state`() = runTest {
        reducer.update(HomeAction.LoadingFailed("Offline"))

        assertEquals(HomeState.Error("Offline"), reducer.state.value)
    }

    @Test
    fun `given Retry when reducer updates then Loading state`() = runTest {
        reducer.update(HomeAction.Retry)

        assertEquals(HomeState.Loading, reducer.state.value)
    }
}
