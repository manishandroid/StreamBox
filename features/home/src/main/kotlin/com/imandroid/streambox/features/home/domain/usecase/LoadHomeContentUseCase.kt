package com.imandroid.streambox.features.home.domain.usecase

import com.imandroid.streambox.core.architecture.UseCase
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.repository.HomeContentRepository

interface LoadHomeContentUseCase : UseCase.Suspending<Result<List<HomeContent>>>

class LoadHomeContentUseCaseImpl(
    private val repository: HomeContentRepository
) : LoadHomeContentUseCase {

    override suspend fun invoke(): Result<List<HomeContent>> {
        return repository.loadHomeContent()
    }
}
