package com.imandroid.streambox.features.home.data.di

import com.imandroid.streambox.features.home.data.HomeContentRepositoryImpl
import com.imandroid.streambox.features.home.data.local.HomeContentLocalDataSource
import com.imandroid.streambox.features.home.data.local.HomeContentLocalDataSourceImpl
import com.imandroid.streambox.features.home.data.mediator.HomeOfflineMediator
import com.imandroid.streambox.features.home.data.mediator.HomeOfflineMediatorImpl
import com.imandroid.streambox.features.home.data.remote.HomeContentRemoteDataSource
import com.imandroid.streambox.features.home.data.remote.HomeContentRemoteDataSourceImpl
import com.imandroid.streambox.features.home.domain.repository.HomeContentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDataModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        impl: HomeContentRemoteDataSourceImpl
    ): HomeContentRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(
        impl: HomeContentLocalDataSourceImpl
    ): HomeContentLocalDataSource

    @Binds
    @Singleton
    abstract fun bindOfflineMediator(
        impl: HomeOfflineMediatorImpl
    ): HomeOfflineMediator

    @Binds
    @Singleton
    abstract fun bindRepository(
        impl: HomeContentRepositoryImpl
    ): HomeContentRepository
}
