package com.imandroid.streambox.di

import com.imandroid.streambox.core.architecture.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

/**
 * Hilt module providing app-level dependencies.
 *
 * ## Pattern
 * Core infrastructure dependencies are provided at the SingletonComponent
 * level to ensure single instances throughout the app lifecycle.
 *
 * ## Current State
 * Provides shared infrastructure dependencies.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides the production DispatcherProvider.
     *
     * For testing, inject TestDispatcherProvider instead via test modules.
     */
    @Provides
    @Singleton
    fun provideDispatcherProvider(): DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineContext = Dispatchers.Main
        override val io: CoroutineContext = Dispatchers.IO
        override val default: CoroutineContext = Dispatchers.Default
    }
}
