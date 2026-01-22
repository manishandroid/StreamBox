package com.imandroid.streambox.features.home.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadHomeContentUseCaseTest {

    @Test
    fun `returns featured content list`() = runTest {
        val useCase = LoadHomeContentUseCaseImpl()

        val deferred = async { useCase() }
        advanceUntilIdle()

        val result = deferred.await()
        assertTrue(result.isSuccess)
        assertEquals(3, result.getOrThrow().size)
    }
}
