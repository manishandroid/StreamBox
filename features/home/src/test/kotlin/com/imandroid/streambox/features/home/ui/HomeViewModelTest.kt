package com.imandroid.streambox.features.home.ui

import com.imandroid.streambox.core.testing.TestDispatcherProvider
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `load action delegates to use case and updates state`() = runTest {
        val useCase = FakeLoadHomeContentUseCase()
        val viewModel = HomeViewModel(
            dispatcherProvider = TestDispatcherProvider(testDispatcher),
            loadHomeContentUseCase = useCase
        )

        viewModel.onAction(HomeAction.Load)
        advanceUntilIdle()

        assertEquals(1, useCase.invocations)
        assertEquals(HomeState.Content(useCase.items), viewModel.state.value)
    }
}

private class FakeLoadHomeContentUseCase : LoadHomeContentUseCase {
    val items = listOf(
        HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi")
    )
    var invocations: Int = 0

    override suspend fun invoke(): Result<List<HomeContent>> {
        invocations += 1
        return Result.success(items)
    }
}
