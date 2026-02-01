package com.imandroid.streambox.features.home.ui

import app.cash.turbine.test
import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.core.testing.TestDispatcherProvider
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCase
import com.imandroid.streambox.features.home.ui.model.HomeContentUi
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @Test
    fun `given Load action when invoked then emits Content state`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val useCase = mockk<LoadHomeContentUseCase>()
        val mapper = mockk<Mapper<List<HomeContent>, List<HomeContentUi>>>()
        val domainItems = listOf(HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi"))
        val uiItems = listOf(HomeContentUi(title = "Night Signal", year = "2024", category = "Sci-Fi"))

        coEvery { useCase.invoke() } returns Result.success(domainItems)
        every { mapper.map(domainItems) } returns uiItems

        val viewModel = HomeViewModel(
            dispatcherProvider = TestDispatcherProvider(testDispatcher),
            loadHomeContentUseCase = useCase,
            homeContentUiMapper = mapper
        )

        viewModel.state.test {
            assertEquals(HomeState.Idle, awaitItem())

            viewModel.onAction(HomeAction.Load)

            assertEquals(HomeState.Loading, awaitItem())
            assertEquals(HomeState.Content(uiItems), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { useCase.invoke() }
        verify(exactly = 1) { mapper.map(domainItems) }
        confirmVerified(useCase, mapper)
    }

    @Test
    fun `given Load action when use case fails then emits Error state`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val useCase = mockk<LoadHomeContentUseCase>()
        val mapper = mockk<Mapper<List<HomeContent>, List<HomeContentUi>>>()

        coEvery { useCase.invoke() } returns Result.failure(IllegalStateException("Offline"))

        val viewModel = HomeViewModel(
            dispatcherProvider = TestDispatcherProvider(testDispatcher),
            loadHomeContentUseCase = useCase,
            homeContentUiMapper = mapper
        )

        viewModel.state.test {
            assertEquals(HomeState.Idle, awaitItem())

            viewModel.onAction(HomeAction.Load)

            assertEquals(HomeState.Loading, awaitItem())
            val errorState = awaitItem() as HomeState.Error
            assertEquals("Offline", errorState.message)
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { useCase.invoke() }
        verify { mapper wasNot Called }
        confirmVerified(useCase, mapper)
    }

    @Test
    fun `given ContentLoaded action when invoked then emits Content state directly`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val useCase = mockk<LoadHomeContentUseCase>(relaxed = true)
        val mapper = mockk<Mapper<List<HomeContent>, List<HomeContentUi>>>()
        val uiItems = listOf(HomeContentUi(title = "Night Signal", year = "2024", category = "Sci-Fi"))

        val viewModel = HomeViewModel(
            dispatcherProvider = TestDispatcherProvider(testDispatcher),
            loadHomeContentUseCase = useCase,
            homeContentUiMapper = mapper
        )

        viewModel.state.test {
            assertEquals(HomeState.Idle, awaitItem())

            viewModel.onAction(HomeAction.ContentLoaded(uiItems))

            assertEquals(HomeState.Content(uiItems), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 0) { useCase.invoke() }
        verify { mapper wasNot Called }
        confirmVerified(useCase, mapper)
    }

    @Test
    fun `given Retry action when invoked then emits Content state`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val useCase = mockk<LoadHomeContentUseCase>()
        val mapper = mockk<Mapper<List<HomeContent>, List<HomeContentUi>>>()
        val domainItems = listOf(HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi"))
        val uiItems = listOf(HomeContentUi(title = "Night Signal", year = "2024", category = "Sci-Fi"))

        coEvery { useCase.invoke() } returns Result.success(domainItems)
        every { mapper.map(domainItems) } returns uiItems

        val viewModel = HomeViewModel(
            dispatcherProvider = TestDispatcherProvider(testDispatcher),
            loadHomeContentUseCase = useCase,
            homeContentUiMapper = mapper
        )

        viewModel.state.test {
            assertEquals(HomeState.Idle, awaitItem())

            viewModel.onAction(HomeAction.Retry)

            assertEquals(HomeState.Loading, awaitItem())
            assertEquals(HomeState.Content(uiItems), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        coVerify(exactly = 1) { useCase.invoke() }
        verify(exactly = 1) { mapper.map(domainItems) }
        confirmVerified(useCase, mapper)
    }

    @Test
    fun `given LoadingFailed action when invoked then emits Error state directly`() = runTest {
        val testDispatcher = StandardTestDispatcher(testScheduler)
        val useCase = mockk<LoadHomeContentUseCase>(relaxed = true)
        val mapper = mockk<Mapper<List<HomeContent>, List<HomeContentUi>>>(relaxed = true)

        val viewModel = HomeViewModel(
            dispatcherProvider = TestDispatcherProvider(testDispatcher),
            loadHomeContentUseCase = useCase,
            homeContentUiMapper = mapper
        )

        viewModel.state.test {
            assertEquals(HomeState.Idle, awaitItem())

            viewModel.onAction(HomeAction.LoadingFailed("Offline"))

            assertEquals(HomeState.Error("Offline"), awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

        verify { useCase wasNot Called }
        verify { mapper wasNot Called }
        confirmVerified(useCase, mapper)
    }
}
