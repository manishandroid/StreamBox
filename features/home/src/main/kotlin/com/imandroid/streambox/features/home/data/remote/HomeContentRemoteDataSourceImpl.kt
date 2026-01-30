package com.imandroid.streambox.features.home.data.remote

import com.imandroid.streambox.features.home.data.network.HomeContentApi
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import javax.inject.Inject

class HomeContentRemoteDataSourceImpl @Inject constructor(
    private val api: HomeContentApi
) : HomeContentRemoteDataSource {
    override suspend fun fetchHomeContent(): Result<List<HomeContentDto>> =
        runCatching { api.fetchHomeContent() }
}
