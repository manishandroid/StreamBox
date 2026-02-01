package com.imandroid.streambox.features.home.data.remote

import com.imandroid.streambox.features.home.data.network.HomeContentApi
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Test

class HomeContentRemoteDataSourceImplTest {

    @Test
    fun `given api success when fetch then returns success`() = runTest {
        val api = mockk<HomeContentApi>()
        coEvery { api.fetchHomeContent() } returns listOf(HomeContentDto(name = "Night Signal"))
        val dataSource = HomeContentRemoteDataSourceImpl(api)

        val result = dataSource.fetchHomeContent()

        assertTrue(result.isSuccess)
        coVerify(exactly = 1) { api.fetchHomeContent() }
    }

    @Test
    fun `given api failure when fetch then returns failure`() = runTest {
        val api = mockk<HomeContentApi>()
        coEvery { api.fetchHomeContent() } throws IllegalStateException("Network failed")
        val dataSource = HomeContentRemoteDataSourceImpl(api)

        val result = dataSource.fetchHomeContent()

        assertTrue(result.isFailure)
        coVerify(exactly = 1) { api.fetchHomeContent() }
    }
}
