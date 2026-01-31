package com.imandroid.streambox.features.home.data.mapper

import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import com.imandroid.streambox.features.home.domain.HomeContent
import javax.inject.Inject

private const val UNKNOWN_YEAR = "—"
private const val DEFAULT_CATEGORY = "General"

class HomeContentDtoMapper @Inject constructor() : Mapper<HomeContentDto, HomeContent> {
    override fun map(input: HomeContentDto): HomeContent {
        val year = input.premiered?.takeIf { it.length >= 4 }?.substring(0, 4) ?: UNKNOWN_YEAR
        val category = input.genres.firstOrNull() ?: DEFAULT_CATEGORY
        val imageUrl = input.image?.medium ?: input.image?.original
        return HomeContent(
            title = input.name,
            year = year,
            category = category,
            imageUrl = imageUrl
        )
    }
}

class HomeContentDtoListMapper @Inject constructor(
    private val itemMapper: Mapper<HomeContentDto, HomeContent>
) : Mapper<List<HomeContentDto>, List<HomeContent>> {
    override fun map(input: List<HomeContentDto>): List<HomeContent> =
        input.map(itemMapper::map)
}
