package com.imandroid.streambox.features.home.data.mediator

import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.data.local.HomeContentLocalDataSource
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import com.imandroid.streambox.features.home.data.remote.HomeContentRemoteDataSource
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.HomeContentLoadException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeOfflineMediatorImplTest {

    @Test
    fun `given network success when load then writes db and returns domain`() = runTest {
        val remote = mockk<HomeContentRemoteDataSource>()
        val local = mockk<HomeContentLocalDataSource>(relaxed = true)
        val dtoToDomain = mockk<Mapper<List<HomeContentDto>, List<HomeContent>>>()
        val entityToDomain = mockk<Mapper<List<HomeContentEntity>, List<HomeContent>>>()
        val domainToEntity = mockk<Mapper<List<HomeContent>, List<HomeContentEntity>>>()
        val dtoList = listOf(HomeContentDto(name = "Night Signal"))
        val domainList = listOf(HomeContent(title = "Night Signal", year = "2024", category = "Drama"))
        val entityList = listOf(HomeContentEntity(id = "1", title = "Night Signal", year = "2024", category = "Drama", imageUrl = null))

        coEvery { remote.fetchHomeContent() } returns Result.success(dtoList)
        every { dtoToDomain.map(dtoList) } returns domainList
        every { domainToEntity.map(domainList) } returns entityList

        val mediator = HomeOfflineMediatorImpl(
            remoteDataSource = remote,
            localDataSource = local,
            dtoToDomainMapper = dtoToDomain,
            entityToDomainMapper = entityToDomain,
            domainToEntityMapper = domainToEntity
        )

        val result = mediator.loadHomeContent()

        coVerify(exactly = 1) { local.upsertAll(entityList) }
        assertTrue(result.isSuccess)
        assertEquals(1, result.getOrThrow().size)
    }

    @Test
    fun `given network failure and cache when load then returns cached`() = runTest {
        val remote = mockk<HomeContentRemoteDataSource>()
        val local = mockk<HomeContentLocalDataSource>()
        val dtoToDomain = mockk<Mapper<List<HomeContentDto>, List<HomeContent>>>()
        val entityToDomain = mockk<Mapper<List<HomeContentEntity>, List<HomeContent>>>()
        val domainToEntity = mockk<Mapper<List<HomeContent>, List<HomeContentEntity>>>()
        val cachedEntities = listOf(
            HomeContentEntity(id = "1", title = "Cached", year = "2024", category = "Drama", imageUrl = null)
        )
        val cachedDomain = listOf(HomeContent(title = "Cached", year = "2024", category = "Drama"))

        coEvery { remote.fetchHomeContent() } returns Result.failure(IllegalStateException("Network failed"))
        coEvery { local.getAll() } returns cachedEntities
        every { entityToDomain.map(cachedEntities) } returns cachedDomain

        val mediator = HomeOfflineMediatorImpl(
            remoteDataSource = remote,
            localDataSource = local,
            dtoToDomainMapper = dtoToDomain,
            entityToDomainMapper = entityToDomain,
            domainToEntityMapper = domainToEntity
        )

        val result = mediator.loadHomeContent()

        assertTrue(result.isSuccess)
        assertEquals("Cached", result.getOrThrow().first().title)
    }

    @Test
    fun `given network failure and empty cache when load then returns error`() = runTest {
        val remote = mockk<HomeContentRemoteDataSource>()
        val local = mockk<HomeContentLocalDataSource>()
        val dtoToDomain = mockk<Mapper<List<HomeContentDto>, List<HomeContent>>>()
        val entityToDomain = mockk<Mapper<List<HomeContentEntity>, List<HomeContent>>>()
        val domainToEntity = mockk<Mapper<List<HomeContent>, List<HomeContentEntity>>>()

        coEvery { remote.fetchHomeContent() } returns Result.failure(IllegalStateException("Network failed"))
        coEvery { local.getAll() } returns emptyList()

        val mediator = HomeOfflineMediatorImpl(
            remoteDataSource = remote,
            localDataSource = local,
            dtoToDomainMapper = dtoToDomain,
            entityToDomainMapper = entityToDomain,
            domainToEntityMapper = domainToEntity
        )

        val result = mediator.loadHomeContent()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is HomeContentLoadException)
    }
}
