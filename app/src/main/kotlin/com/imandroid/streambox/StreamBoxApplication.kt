package com.imandroid.streambox

import android.app.Application
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
class StreamBoxApplication : Application()
