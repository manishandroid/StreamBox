package com.imandroid.streambox.features.home.data

import com.imandroid.streambox.core.testing.TestDispatcherProvider
import com.imandroid.streambox.features.home.data.mediator.HomeOfflineMediator
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.HomeContentLoadException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeContentRepositoryImplTest {

    @Test
    fun `maps network content to domain content`() = runTest {
        val mediator = FakeMediator(Result.success(listOf(HomeContent("Night Signal", "2024", "Sci-Fi"))))
        val repository = HomeContentRepositoryImpl(
            mediator = mediator,
            dispatcherProvider = TestDispatcherProvider()
        )

        val result = repository.loadHomeContent()

        assertTrue(result.isSuccess)
        assertEquals(1, mediator.invocations)
        assertEquals(1, result.getOrThrow().size)
    }

    @Test
    fun `returns domain failure on network error`() = runTest {
        val mediator = FakeMediator(Result.failure(HomeContentLoadException()))
        val repository = HomeContentRepositoryImpl(
            mediator = mediator,
            dispatcherProvider = TestDispatcherProvider()
        )

        val result = repository.loadHomeContent()

        assertTrue(result.isFailure)
        assertEquals(1, mediator.invocations)
        assertTrue(result.exceptionOrNull() is HomeContentLoadException)
    }
}

private class FakeMediator(
    private val result: Result<List<HomeContent>>
) : HomeOfflineMediator {
    var invocations: Int = 0
    override suspend fun loadHomeContent(): Result<List<HomeContent>> {
        invocations += 1
        return result
    }
}
