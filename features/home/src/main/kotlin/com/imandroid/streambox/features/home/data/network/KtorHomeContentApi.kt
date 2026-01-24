package com.imandroid.streambox.features.home.data.network

import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorHomeContentApi(
    private val client: HttpClient,
    private val baseUrl: String
) : HomeContentApi {
    override suspend fun fetchHomeContent(): List<HomeContentDto> =
        client.get("$baseUrl/shows").body()
}
