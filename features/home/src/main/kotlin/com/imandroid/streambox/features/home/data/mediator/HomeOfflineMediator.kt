package com.imandroid.streambox.features.home.data.mediator

import com.imandroid.streambox.features.home.domain.HomeContent

interface HomeOfflineMediator {
    suspend fun loadHomeContent(): Result<List<HomeContent>>
}
