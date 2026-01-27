package com.imandroid.streambox.features.home.data.local.mapper

import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeContentEntityMapperTest {

    @Test
    fun `maps domain to entity`() {
        val mapper = HomeContentDomainToEntityMapper()
        val domain = HomeContent(title = "Night Signal", year = "2024", category = "Sci-Fi")

        val entity = mapper.map(domain)

        assertEquals("Night Signal", entity.title)
        assertEquals("2024", entity.year)
        assertEquals("Sci-Fi", entity.category)
        assertEquals("Night Signal", entity.id)
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

        assertEquals(HomeContent("Harborline", "2023", "Drama"), domain)
    }
}
