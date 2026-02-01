package com.imandroid.streambox.features.home.data.local.mapper

import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeContentEntityMapperTest {

    @Test
    fun `maps domain to entity`() {
        val mapper = HomeContentDomainToEntityMapper()
        val domain = HomeContent(
            title = "Night Signal",
            year = "2024",
            category = "Sci-Fi",
            imageUrl = "https://example.org/poster.jpg"
        )

        val entity = mapper.map(domain)

        assertEquals("Night Signal", entity.title)
        assertEquals("2024", entity.year)
        assertEquals("Sci-Fi", entity.category)
        assertEquals("Night Signal", entity.id)
        assertEquals("https://example.org/poster.jpg", entity.imageUrl)
    }

    @Test
    fun `maps entity to domain`() {
        val mapper = HomeContentEntityToDomainMapper()
        val entity = HomeContentEntity(
            id = "1",
            title = "Harborline",
            year = "2023",
            category = "Drama",
            imageUrl = null
        )

        val domain = mapper.map(entity)

        assertEquals(HomeContent("Harborline", "2023", "Drama", null), domain)
    }

    @Test
    fun `maps entity list to domain list`() {
        val itemMapper = HomeContentEntityToDomainMapper()
        val listMapper = HomeContentEntityListMapper(itemMapper)
        val entities = listOf(
            HomeContentEntity(id = "1", title = "Night Signal", year = "2024", category = "Sci-Fi", imageUrl = null),
            HomeContentEntity(id = "2", title = "Harborline", year = "2023", category = "Drama", imageUrl = null)
        )

        val result = listMapper.map(entities)

        assertEquals(2, result.size)
        assertEquals("Night Signal", result.first().title)
        assertEquals("Harborline", result.last().title)
    }

    @Test
    fun `maps domain list to entity list`() {
        val itemMapper = HomeContentDomainToEntityMapper()
        val listMapper = HomeContentDomainListToEntityMapper(itemMapper)
        val domain = listOf(
            HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi"),
            HomeContent(title = "Harborline", year = "2023", category = "Drama")
        )

        val result = listMapper.map(domain)

        assertEquals(2, result.size)
        assertEquals("Night Signal", result.first().title)
        assertEquals("Harborline", result.last().title)
    }
}
