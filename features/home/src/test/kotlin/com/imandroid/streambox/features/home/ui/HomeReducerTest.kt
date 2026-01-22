package com.imandroid.streambox.features.home.ui

import com.imandroid.streambox.core.testing.TestDispatcherProvider
import com.imandroid.streambox.features.home.domain.HomeContent
import kotlinx.coroutines.runBlocking
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
    fun `load action transitions to Loading state`() {
        runBlocking {
            reducer.update(HomeAction.Load)

            assertEquals(HomeState.Loading, reducer.state.value)
        }
    }

    @Test
    fun `content loaded action transitions to Content state`() {
        runBlocking {
            val items = listOf(
                HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi")
            )

            reducer.update(HomeAction.ContentLoaded(items))

            assertEquals(HomeState.Content(items), reducer.state.value)
        }
    }

    @Test
    fun `loading failed action transitions to Error state`() {
        runBlocking {
            reducer.update(HomeAction.LoadingFailed("Offline"))

            assertEquals(HomeState.Error("Offline"), reducer.state.value)
        }
    }

    @Test
    fun `retry action transitions to Loading state`() {
        runBlocking {
            reducer.update(HomeAction.Retry)

            assertEquals(HomeState.Loading, reducer.state.value)
        }
    }
}
