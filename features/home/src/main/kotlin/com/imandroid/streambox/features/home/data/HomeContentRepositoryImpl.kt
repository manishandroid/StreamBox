package com.imandroid.streambox.features.home.data

import com.imandroid.streambox.core.architecture.DispatcherProvider
import com.imandroid.streambox.features.home.data.mediator.HomeOfflineMediator
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.repository.HomeContentRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HomeContentRepositoryImpl @Inject constructor(
    private val mediator: HomeOfflineMediator,
    private val dispatcherProvider: DispatcherProvider
) : HomeContentRepository {
    override suspend fun loadHomeContent(): Result<List<HomeContent>> =
        withContext(dispatcherProvider.io) {
            mediator.loadHomeContent()
        }
}
