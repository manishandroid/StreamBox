package com.imandroid.streambox.features.home.data.mediator

import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.data.local.HomeContentLocalDataSource
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import com.imandroid.streambox.features.home.data.remote.HomeContentRemoteDataSource
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.HomeContentLoadException
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeOfflineMediatorImplTest {

    @Test
    fun `network success writes to db and returns domain`() = runTest {
        val remote = FakeRemote(Result.success(listOf(HomeContentDto(name = "Night Signal"))))
        val local = FakeLocal()
        val mediator = HomeOfflineMediatorImpl(
            remoteDataSource = remote,
            localDataSource = local,
            dtoToDomainMapper = IdentityDtoToDomainMapper(),
            entityToDomainMapper = IdentityEntityToDomainMapper(),
            domainToEntityMapper = IdentityDomainToEntityMapper()
        )

        val result = mediator.loadHomeContent()

        assertTrue(result.isSuccess)
        assertEquals(1, local.upsertCalls)
        assertEquals(1, result.getOrThrow().size)
    }

    @Test
    fun `network failure falls back to db when available`() = runTest {
        val remote = FakeRemote(Result.failure(IllegalStateException("Network failed")))
        val local = FakeLocal(
            stored = listOf(HomeContentEntity(id = "1", title = "Cached", year = "2024", category = "Drama", imageUrl = null))
        )
        val mediator = HomeOfflineMediatorImpl(
            remoteDataSource = remote,
            localDataSource = local,
            dtoToDomainMapper = IdentityDtoToDomainMapper(),
            entityToDomainMapper = IdentityEntityToDomainMapper(),
            domainToEntityMapper = IdentityDomainToEntityMapper()
        )

        val result = mediator.loadHomeContent()

        assertTrue(result.isSuccess)
        assertEquals("Cached", result.getOrThrow().first().title)
    }

    @Test
    fun `network failure returns error when db is empty`() = runTest {
        val remote = FakeRemote(Result.failure(IllegalStateException("Network failed")))
        val local = FakeLocal()
        val mediator = HomeOfflineMediatorImpl(
            remoteDataSource = remote,
            localDataSource = local,
            dtoToDomainMapper = IdentityDtoToDomainMapper(),
            entityToDomainMapper = IdentityEntityToDomainMapper(),
            domainToEntityMapper = IdentityDomainToEntityMapper()
        )

        val result = mediator.loadHomeContent()

        assertTrue(result.isFailure)
        assertTrue(result.exceptionOrNull() is HomeContentLoadException)
    }
}

private class FakeRemote(
    private val result: Result<List<HomeContentDto>>
) : HomeContentRemoteDataSource {
    override suspend fun fetchHomeContent(): Result<List<HomeContentDto>> = result
}

private class FakeLocal(
    private val stored: List<HomeContentEntity> = emptyList()
) : HomeContentLocalDataSource {
    var upsertCalls: Int = 0

    override fun observeAll() = throw UnsupportedOperationException()

    override suspend fun getAll(): List<HomeContentEntity> = stored

    override suspend fun upsertAll(items: List<HomeContentEntity>) {
        upsertCalls += 1
    }

    override suspend fun clearAll() = Unit
}

private class IdentityDtoToDomainMapper : Mapper<List<HomeContentDto>, List<HomeContent>> {
    override fun map(input: List<HomeContentDto>): List<HomeContent> =
        input.map { HomeContent(title = it.name, year = "2024", category = "Drama") }
}

private class IdentityEntityToDomainMapper : Mapper<List<HomeContentEntity>, List<HomeContent>> {
    override fun map(input: List<HomeContentEntity>): List<HomeContent> =
        input.map { HomeContent(title = it.title, year = it.year, category = it.category) }
}

private class IdentityDomainToEntityMapper : Mapper<List<HomeContent>, List<HomeContentEntity>> {
    override fun map(input: List<HomeContent>): List<HomeContentEntity> =
        input.map { HomeContentEntity(id = it.title, title = it.title, year = it.year, category = it.category, imageUrl = null) }
}
