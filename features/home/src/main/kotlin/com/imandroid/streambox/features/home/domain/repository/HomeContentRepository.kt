package com.imandroid.streambox.features.home.domain.repository

import com.imandroid.streambox.features.home.domain.HomeContent

interface HomeContentRepository {
    suspend fun loadHomeContent(): Result<List<HomeContent>>
}
