package com.imandroid.streambox.features.home.data.network

import com.imandroid.streambox.core.network.KtorClientProvider

object HomeNetworkModule {

    private const val BASE_URL = "https://api.tvmaze.com"

    fun provideHomeContentApi(): HomeContentApi =
        KtorHomeContentApi(
            client = KtorClientProvider.client,
            baseUrl = BASE_URL
        )
}
