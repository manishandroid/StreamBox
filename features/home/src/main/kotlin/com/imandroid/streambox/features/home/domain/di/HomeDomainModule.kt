package com.imandroid.streambox.features.home.domain.di

import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCase
import com.imandroid.streambox.features.home.domain.usecase.LoadHomeContentUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeDomainModule {

    @Binds
    @Singleton
    abstract fun bindLoadHomeContentUseCase(
        impl: LoadHomeContentUseCaseImpl
    ): LoadHomeContentUseCase
}
