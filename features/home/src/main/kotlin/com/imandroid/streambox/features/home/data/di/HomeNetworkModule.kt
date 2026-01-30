package com.imandroid.streambox.features.home.data.di

import com.imandroid.streambox.features.home.data.network.HomeContentApi
import com.imandroid.streambox.features.home.data.network.KtorHomeContentApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeNetworkModule {

    private const val BASE_URL = "https://api.tvmaze.com"

    @Provides
    @Singleton
    fun provideHomeContentApi(
        client: HttpClient
    ): HomeContentApi = KtorHomeContentApi(
        client = client,
        baseUrl = BASE_URL
    )
}
