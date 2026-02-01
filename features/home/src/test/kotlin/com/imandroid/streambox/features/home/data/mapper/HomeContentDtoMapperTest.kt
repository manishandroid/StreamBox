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

    @Test
    fun `uses original image when medium is missing`() {
        val mapper = HomeContentDtoMapper()
        val dto = HomeContentDto(
            name = "Signal",
            premiered = "2024-01-01",
            genres = listOf("Sci-Fi"),
            image = HomeContentImageDto(
                medium = null,
                original = "https://example.org/original.jpg"
            )
        )

        val domain = mapper.map(dto)

        assertEquals("https://example.org/original.jpg", domain.imageUrl)
    }

    @Test
    fun `uses default year when premiered is too short`() {
        val mapper = HomeContentDtoMapper()
        val dto = HomeContentDto(
            name = "Short Date",
            premiered = "20",
            genres = listOf("Drama"),
            image = HomeContentImageDto(
                medium = "https://example.org/medium.jpg",
                original = null
            )
        )

        val domain = mapper.map(dto)

        assertEquals("—", domain.year)
        assertEquals("Drama", domain.category)
        assertEquals("https://example.org/medium.jpg", domain.imageUrl)
    }

    @Test
    fun `maps dto list to domain list`() {
        val itemMapper = HomeContentDtoMapper()
        val listMapper = HomeContentDtoListMapper(itemMapper)
        val dtoList = listOf(
            HomeContentDto(name = "Night Signal", premiered = "2024-01-01", genres = listOf("Sci-Fi")),
            HomeContentDto(name = "Harborline", premiered = "2023-01-01", genres = listOf("Drama"))
        )

        val result = listMapper.map(dtoList)

        assertEquals(2, result.size)
        assertEquals("Night Signal", result.first().title)
        assertEquals("Harborline", result.last().title)
    }
}
