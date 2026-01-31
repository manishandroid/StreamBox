package com.imandroid.streambox.features.home.data.mapper

import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import com.imandroid.streambox.features.home.data.network.dto.HomeContentImageDto
import com.imandroid.streambox.features.home.domain.HomeContent
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeContentDtoMapperTest {

    @Test
    fun `maps dto to domain content`() {
        val mapper = HomeContentDtoMapper()
        val dto = HomeContentDto(
            name = "Night Signal",
            premiered = "2024-01-01",
            genres = listOf("Sci-Fi"),
            image = HomeContentImageDto(
                medium = "https://example.org/medium.jpg",
                original = "https://example.org/original.jpg"
            )
        )

        val domain = mapper.map(dto)

        assertEquals(
            HomeContent("Night Signal", "2024", "Sci-Fi", "https://example.org/medium.jpg"),
            domain
        )
    }

    @Test
    fun `handles missing fields with defaults`() {
        val mapper = HomeContentDtoMapper()
        val dto = HomeContentDto(
            name = "Harborline",
            premiered = null,
            genres = emptyList(),
            image = null
        )

        val domain = mapper.map(dto)

        assertEquals(HomeContent("Harborline", "—", "General", null), domain)
    }
}
