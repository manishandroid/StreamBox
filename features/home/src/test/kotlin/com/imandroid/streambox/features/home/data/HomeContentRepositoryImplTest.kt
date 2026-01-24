package com.imandroid.streambox.features.home.data

import com.imandroid.streambox.core.testing.TestDispatcherProvider
import com.imandroid.streambox.features.home.data.mapper.HomeContentDtoListMapper
import com.imandroid.streambox.features.home.data.mapper.HomeContentDtoMapper
import com.imandroid.streambox.features.home.data.network.HomeContentApi
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
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
        val repository = HomeContentRepositoryImpl(
            api = FakeHomeContentApi(),
            mapper = HomeContentDtoListMapper(HomeContentDtoMapper()),
            dispatcherProvider = TestDispatcherProvider()
        )

        val result = repository.loadHomeContent()

        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrThrow().size)
    }

    @Test
    fun `returns domain failure on network error`() = runTest {
        val repository = HomeContentRepositoryImpl(
            api = FailingHomeContentApi(),
            mapper = HomeContentDtoListMapper(HomeContentDtoMapper()),
            dispatcherProvider = TestDispatcherProvider()
        )

        val result = repository.loadHomeContent()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is HomeContentLoadException)
    }
}

private class FakeHomeContentApi : HomeContentApi {
    override suspend fun fetchHomeContent(): List<HomeContentDto> = listOf(
        HomeContentDto(name = "Night Signal", premiered = "2024-01-01", genres = listOf("Sci-Fi")),
        HomeContentDto(name = "Harborline", premiered = "2023-02-01", genres = listOf("Drama"))
    )
}

private class FailingHomeContentApi : HomeContentApi {
    override suspend fun fetchHomeContent(): List<HomeContentDto> {
        throw IllegalStateException("Network failed")
    }
}
