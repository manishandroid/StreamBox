package com.imandroid.streambox.features.home.domain.usecase

import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.repository.HomeContentRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadHomeContentUseCaseTest {

    @Test
    fun `returns featured content list`() = runTest {
        val repository = FakeHomeContentRepository()
        val useCase = LoadHomeContentUseCaseImpl(repository)

        val result = useCase()
        assertTrue(result.isSuccess)
        assertEquals(1, repository.invocations)
        assertEquals(2, result.getOrThrow().size)
    }

    @Test
    fun `returns empty list when repository is empty`() = runTest {
        val repository = EmptyHomeContentRepository()
        val useCase = LoadHomeContentUseCaseImpl(repository)

        val result = useCase()

        assertTrue(result.isSuccess)
        assertEquals(0, result.getOrThrow().size)
    }
}

private class FakeHomeContentRepository : HomeContentRepository {
    var invocations: Int = 0
    private val items = listOf(
        HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi"),
        HomeContent(title = "Harborline", year = "2023", category = "Drama")
    )

    override suspend fun loadHomeContent(): Result<List<HomeContent>> {
        invocations += 1
        return Result.success(items)
    }
}

private class EmptyHomeContentRepository : HomeContentRepository {
    override suspend fun loadHomeContent(): Result<List<HomeContent>> =
        Result.success(emptyList())
}
