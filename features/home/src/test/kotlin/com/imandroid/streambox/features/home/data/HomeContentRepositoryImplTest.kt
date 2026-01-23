package com.imandroid.streambox.features.home.data

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeContentRepositoryImplTest {

    @Test
    fun `returns deterministic home content list`() = runTest {
        val repository = HomeContentRepositoryImpl()

        val deferred = async { repository.loadHomeContent() }
        advanceUntilIdle()

        val result = deferred.await()
        assertTrue(result.isSuccess)
        assertEquals(3, result.getOrThrow().size)
    }
}
