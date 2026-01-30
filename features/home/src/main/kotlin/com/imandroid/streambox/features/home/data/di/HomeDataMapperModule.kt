package com.imandroid.streambox.features.home.data.di

import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.data.local.db.HomeContentEntity
import com.imandroid.streambox.features.home.data.local.mapper.HomeContentDomainListToEntityMapper
import com.imandroid.streambox.features.home.data.local.mapper.HomeContentDomainToEntityMapper
import com.imandroid.streambox.features.home.data.local.mapper.HomeContentEntityListMapper
import com.imandroid.streambox.features.home.data.local.mapper.HomeContentEntityToDomainMapper
import com.imandroid.streambox.features.home.data.mapper.HomeContentDtoListMapper
import com.imandroid.streambox.features.home.data.mapper.HomeContentDtoMapper
import com.imandroid.streambox.features.home.data.network.dto.HomeContentDto
import com.imandroid.streambox.features.home.domain.HomeContent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeDataMapperModule {

    @Provides
    fun provideDtoItemMapper(): Mapper<HomeContentDto, HomeContent> =
        HomeContentDtoMapper()

    @Provides
    fun provideDtoListMapper(
        mapper: Mapper<HomeContentDto, HomeContent>
    ): Mapper<List<HomeContentDto>, List<HomeContent>> =
        HomeContentDtoListMapper(mapper)

    @Provides
    fun provideEntityToDomainMapper(): Mapper<HomeContentEntity, HomeContent> =
        HomeContentEntityToDomainMapper()

    @Provides
    fun provideEntityListMapper(
        mapper: Mapper<HomeContentEntity, HomeContent>
    ): Mapper<List<HomeContentEntity>, List<HomeContent>> =
        HomeContentEntityListMapper(mapper)

    @Provides
    fun provideDomainToEntityMapper(): Mapper<HomeContent, HomeContentEntity> =
        HomeContentDomainToEntityMapper()

    @Provides
    fun provideDomainListToEntityMapper(
        mapper: Mapper<HomeContent, HomeContentEntity>
    ): Mapper<List<HomeContent>, List<HomeContentEntity>> =
        HomeContentDomainListToEntityMapper(mapper)
}
