package com.imandroid.streambox.features.home.mapper

import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.ui.model.HomeContentUi
import org.junit.Assert.assertEquals
import org.junit.Test

class HomeContentUiMapperTest {

    @Test
    fun `maps domain content to ui content`() {
        val mapper = HomeContentUiMapper()
        val domain = HomeContent(
            title = "Night Signal",
            year = "2024",
            category = "Sci-Fi",
            imageUrl = "https://example.org/poster.jpg"
        )

        val ui = mapper.map(domain)

        assertEquals(HomeContentUi("Night Signal", "2024", "Sci-Fi", "https://example.org/poster.jpg"), ui)
    }

    @Test
    fun `maps empty list to empty list`() {
        val mapper = HomeContentUiListMapper(HomeContentUiMapper())

        val uiItems = mapper.map(emptyList())

        assertEquals(emptyList<HomeContentUi>(), uiItems)
    }
}
