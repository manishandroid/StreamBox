package com.imandroid.streambox.features.home.snapshot

import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.imandroid.streambox.core.designsystem.theme.StreamBoxTheme
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
abstract class LightDarkSnapshotTest(
    private val darkTheme: Boolean
) {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5
    )

    protected fun snapshot(content: @androidx.compose.runtime.Composable () -> Unit) {
        paparazzi.snapshot {
            StreamBoxTheme(darkTheme = darkTheme) {
                content()
            }
        }
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "dark={0}")
        fun parameters(): List<Array<Any>> = listOf(
            arrayOf(false),
            arrayOf(true)
        )
    }
}
