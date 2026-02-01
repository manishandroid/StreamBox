package com.imandroid.streambox.features.home.domain.usecase

import com.imandroid.streambox.features.home.domain.HomeContent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadHomeContentUseCaseTest {

    @Test
    fun `given repository success when invoked then returns items`() = runTest {
        val repository = mockk<com.imandroid.streambox.features.home.domain.repository.HomeContentRepository>()
        val items = listOf(
            HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi"),
            HomeContent(title = "Harborline", year = "2023", category = "Drama")
        )
        coEvery { repository.loadHomeContent() } returns Result.success(items)
        val useCase = LoadHomeContentUseCaseImpl(repository)

        val result = useCase()

        coVerify(exactly = 1) { repository.loadHomeContent() }
        assertTrue(result.isSuccess)
        assertEquals(2, result.getOrThrow().size)
    }

    @Test
    fun `given repository empty when invoked then returns empty list`() = runTest {
        val repository = mockk<com.imandroid.streambox.features.home.domain.repository.HomeContentRepository>()
        coEvery { repository.loadHomeContent() } returns Result.success(emptyList())
        val useCase = LoadHomeContentUseCaseImpl(repository)

        val result = useCase()

        coVerify(exactly = 1) { repository.loadHomeContent() }
        assertTrue(result.isSuccess)
        assertEquals(0, result.getOrThrow().size)
    }
}
