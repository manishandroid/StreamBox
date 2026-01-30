package com.imandroid.streambox.features.home.ui.di

import com.imandroid.streambox.core.architecture.Mapper
import com.imandroid.streambox.features.home.domain.HomeContent
import com.imandroid.streambox.features.home.mapper.HomeContentUiListMapper
import com.imandroid.streambox.features.home.mapper.HomeContentUiMapper
import com.imandroid.streambox.features.home.ui.model.HomeContentUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomePresentationModule {

    @Provides
    fun provideUiItemMapper(): Mapper<HomeContent, HomeContentUi> =
        HomeContentUiMapper()

    @Provides
    fun provideUiListMapper(
        mapper: Mapper<HomeContent, HomeContentUi>
    ): Mapper<List<HomeContent>, List<HomeContentUi>> =
        HomeContentUiListMapper(mapper)
}
