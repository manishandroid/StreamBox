package com.imandroid.streambox

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.gif.AnimatedImageDecoder
import coil3.network.ktor2.KtorNetworkFetcherFactory
import coil3.svg.SvgDecoder
import dagger.hilt.android.HiltAndroidApp

/**
 * StreamBox Application class.
 *
 * ## Pattern
 * The Application class is annotated with @HiltAndroidApp to enable
 * dependency injection throughout the app.
 *
 * ## Responsibilities
 * - Initialize Hilt dependency injection
 * - App-level configuration (in future branches)
 *
 * Note: On the main branch, this class is intentionally minimal.
 * SDK initializations, crash reporting, and other bootstrapping
 * will be added in feature branches as needed.
 */
@HiltAndroidApp
class StreamBoxApplication : Application(), SingletonImageLoader.Factory {
    @RequiresApi(Build.VERSION_CODES.P)
    override fun newImageLoader(context: PlatformContext): ImageLoader =
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory())
                add(AnimatedImageDecoder.Factory())
                add(SvgDecoder.Factory())
            }
            .build()
}
