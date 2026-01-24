package com.imandroid.streambox.features.home.data.network

import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto

interface HomeContentApi {
    suspend fun fetchHomeContent(): List<HomeContentDto>
}
