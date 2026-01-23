package com.imandroid.streambox.features.home.mapper

import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.ui.model.HomeContentUi

class HomeContentUiMapper : Mapper<HomeContent, HomeContentUi> {
    override fun map(input: HomeContent): HomeContentUi = HomeContentUi(
        title = input.title,
        year = input.year,
        category = input.category
    )
}

class HomeContentUiListMapper(
    private val itemMapper: Mapper<HomeContent, HomeContentUi>
) : Mapper<List<HomeContent>, List<HomeContentUi>> {
    override fun map(input: List<HomeContent>): List<HomeContentUi> =
        input.map(itemMapper::map)
}
