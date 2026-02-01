package com.imandroid.streambox.features.home.data

import com.imandroid.streambox.core.testing.TestDispatcherProvider
import com.imandroid.streambox.features.home.data.mediator.HomeOfflineMediator
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.HomeContentLoadException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeContentRepositoryImplTest {

    @Test
    fun `given mediator success when load then returns domain list`() = runTest {
        val mediator = mockk<HomeOfflineMediator>()
        coEvery { mediator.loadHomeContent() } returns Result.success(
            listOf(HomeContent("Night Signal", "2024", "Sci-Fi"))
        )
        val repository = HomeContentRepositoryImpl(
            mediator = mediator,
            dispatcherProvider = TestDispatcherProvider()
        )

        val result = repository.loadHomeContent()

        coVerify(exactly = 1) { mediator.loadHomeContent() }
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrThrow().size)
    }

    @Test
    fun `given mediator failure when load then returns error`() = runTest {
        val mediator = mockk<HomeOfflineMediator>()
        coEvery { mediator.loadHomeContent() } returns Result.failure(HomeContentLoadException())
        val repository = HomeContentRepositoryImpl(
            mediator = mediator,
            dispatcherProvider = TestDispatcherProvider()
        )

        val result = repository.loadHomeContent()

        coVerify(exactly = 1) { mediator.loadHomeContent() }
        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is HomeContentLoadException)
    }
}
