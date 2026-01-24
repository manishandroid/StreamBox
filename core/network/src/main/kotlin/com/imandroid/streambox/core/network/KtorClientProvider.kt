package com.imandroid.streambox.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object KtorClientProvider {

    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
        explicitNulls = false
    }

    val client: HttpClient by lazy {
        HttpClient(OkHttp) {
            install(ContentNegotiation) {
                json(json)
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15_000
                connectTimeoutMillis = 15_000
                socketTimeoutMillis = 15_000
            }
        }
    }
}
