package com.imandroid.streambox.features.home.data.mediator

import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.data.local.HomeContentLocalDataSource
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import com.imandroid.streambox.features.home.data.remote.HomeContentRemoteDataSource
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.HomeContentLoadException
import javax.inject.Inject

class HomeOfflineMediatorImpl @Inject constructor(
    private val remoteDataSource: HomeContentRemoteDataSource,
    private val localDataSource: HomeContentLocalDataSource,
    private val dtoToDomainMapper: Mapper<List<HomeContentDto>, List<HomeContent>>,
    private val entityToDomainMapper: Mapper<List<HomeContentEntity>, List<HomeContent>>,
    private val domainToEntityMapper: Mapper<List<HomeContent>, List<HomeContentEntity>>
) : HomeOfflineMediator {

    override suspend fun loadHomeContent(): Result<List<HomeContent>> {
        val remoteResult = remoteDataSource.fetchHomeContent()
        return remoteResult.fold(
            onSuccess = { dtoList ->
                val domain = dtoToDomainMapper.map(dtoList)
                localDataSource.upsertAll(domainToEntityMapper.map(domain))
                Result.success(domain)
            },
            onFailure = {
                val cached = localDataSource.getAll()
                if (cached.isNotEmpty()) {
                    Result.success(entityToDomainMapper.map(cached))
                } else {
                    Result.failure(HomeContentLoadException())
                }
            }
        )
    }
}
