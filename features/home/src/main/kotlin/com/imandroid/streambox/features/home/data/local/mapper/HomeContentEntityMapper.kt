package com.imandroid.streambox.features.home.data.local.mapper

import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import javax.inject.Inject

class HomeContentEntityToDomainMapper @Inject constructor() : Mapper<HomeContentEntity, HomeContent> {
    override fun map(input: HomeContentEntity): HomeContent = HomeContent(
        title = input.title,
        year = input.year,
        category = input.category,
        imageUrl = input.imageUrl
    )
}

class HomeContentDomainToEntityMapper @Inject constructor() : Mapper<HomeContent, HomeContentEntity> {
    override fun map(input: HomeContent): HomeContentEntity = HomeContentEntity(
        id = input.title,
        title = input.title,
        year = input.year,
        category = input.category,
        imageUrl = input.imageUrl
    )
}

class HomeContentEntityListMapper @Inject constructor(
    private val itemMapper: Mapper<HomeContentEntity, HomeContent>
) : Mapper<List<HomeContentEntity>, List<HomeContent>> {
    override fun map(input: List<HomeContentEntity>): List<HomeContent> =
        input.map(itemMapper::map)
}

class HomeContentDomainListToEntityMapper @Inject constructor(
    private val itemMapper: Mapper<HomeContent, HomeContentEntity>
) : Mapper<List<HomeContent>, List<HomeContentEntity>> {
    override fun map(input: List<HomeContent>): List<HomeContentEntity> =
        input.map(itemMapper::map)
}
