package com.imandroid.streambox.features.home.data

import com.imandroid.streambox.core.architecture.DispatcherProvider
import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.data.network.HomeContentApi
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.HomeContentLoadException
import com.imandroid.streambox.features.home.domain.repository.HomeContentRepository
import kotlinx.coroutines.withContext

class HomeContentRepositoryImpl(
    private val api: HomeContentApi,
    private val mapper: Mapper<List<HomeContentDto>, List<HomeContent>>,
    private val dispatcherProvider: DispatcherProvider
) : HomeContentRepository {
    override suspend fun loadHomeContent(): Result<List<HomeContent>> =
        withContext(dispatcherProvider.io) {
            runCatching {
                mapper.map(api.fetchHomeContent())
            }.recoverCatching {
                throw HomeContentLoadException()
            }
        }
}
