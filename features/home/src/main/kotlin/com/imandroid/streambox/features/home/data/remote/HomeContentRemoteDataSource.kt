package com.imandroid.streambox.features.home.data.remote

import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto

interface HomeContentRemoteDataSource {
    suspend fun fetchHomeContent(): Result<List<HomeContentDto>>
}
