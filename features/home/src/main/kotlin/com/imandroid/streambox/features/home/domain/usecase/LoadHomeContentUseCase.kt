package com.imandroid.streambox.features.home.domain.usecase

import com.imandroid.streambox.core.architecture.UseCase
import com.imandroid.streambox.features.home.domain.HomeContent
import kotlinx.coroutines.delay

interface LoadHomeContentUseCase : UseCase.Suspending<Result<List<HomeContent>>>

class LoadHomeContentUseCaseImpl : LoadHomeContentUseCase {

    override suspend fun invoke(): Result<List<HomeContent>> {
        // Simulated work until a real data source is introduced.
        delay(1200L)
        return Result.success(
            listOf(
                HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi"),
                HomeContent(title = "Harborline", year = "2023", category = "Drama"),
                HomeContent(title = "Glass District", year = "2022", category = "Thriller")
            )
        )
    }
}
