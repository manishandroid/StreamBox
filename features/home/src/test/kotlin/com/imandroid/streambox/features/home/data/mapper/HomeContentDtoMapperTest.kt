package com.imandroid.streambox.features.home.data.mapper

import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
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
            genres = listOf("Sci-Fi")
        )

        val domain = mapper.map(dto)

        assertEquals(HomeContent("Night Signal", "2024", "Sci-Fi"), domain)
    }

    @Test
    fun `handles missing fields with defaults`() {
        val mapper = HomeContentDtoMapper()
        val dto = HomeContentDto(
            name = "Harborline",
            premiered = null,
            genres = emptyList()
        )

        val domain = mapper.map(dto)

        assertEquals(HomeContent("Harborline", "—", "General"), domain)
    }
}
