package com.imandroid.streambox.features.home.data.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.HttpHeaders
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class KtorHomeContentApiTest {

    @Test
    fun `parses home content response`() = runTest {
        val jsonBody = """
            [
              { "name": "Night Signal", "premiered": "2024-01-01", "genres": ["Sci-Fi"] },
              { "name": "Harborline", "premiered": null, "genres": [] }
            ]
        """.trimIndent()

        val engine = MockEngine {
            respond(
                content = jsonBody,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = HttpClient(engine) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        val api = KtorHomeContentApi(client, baseUrl = "https://example.org")
        val result = api.fetchHomeContent()

        assertEquals(2, result.size)
        assertEquals("Night Signal", result.first().name)
    }
}
