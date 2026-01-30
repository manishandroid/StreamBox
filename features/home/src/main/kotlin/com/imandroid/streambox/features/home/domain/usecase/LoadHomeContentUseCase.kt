package com.imandroid.streambox.features.home.domain.usecase

import com.imandroid.streambox.core.architecture.UseCase
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.repository.HomeContentRepository
import javax.inject.Inject

interface LoadHomeContentUseCase : UseCase.Suspending<Result<List<HomeContent>>>

class LoadHomeContentUseCaseImpl @Inject constructor(
    private val repository: HomeContentRepository
) : LoadHomeContentUseCase {

    override suspend fun invoke(): Result<List<HomeContent>> {
        return repository.loadHomeContent()
    }
}
