package com.imandroid.streambox

import android.app.Application
import com.imandroid.streambox.db.StreamBoxDatabaseProvider
import com.imandroid.streambox.features.home.data.local.db.HomeDatabaseAccessor
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
class StreamBoxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        StreamBoxDatabaseProvider.init(this)
        HomeDatabaseAccessor.init { StreamBoxDatabaseProvider.get().homeContentDao() }
    }
}
