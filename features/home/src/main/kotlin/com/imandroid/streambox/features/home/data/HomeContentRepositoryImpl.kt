package com.imandroid.streambox.features.home.data

import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.domain.repository.HomeContentRepository
import kotlinx.coroutines.delay

class HomeContentRepositoryImpl : HomeContentRepository {
    override suspend fun loadHomeContent(): Result<List<HomeContent>> =
        run {
            // Simulate I/O latency until a real data source is introduced.
            delay(1200L)
            Result.success(
                listOf(
                    HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi"),
                    HomeContent(title = "Harborline", year = "2023", category = "Drama"),
                    HomeContent(title = "Glass District", year = "2022", category = "Thriller")
                )
            )
        }
}
