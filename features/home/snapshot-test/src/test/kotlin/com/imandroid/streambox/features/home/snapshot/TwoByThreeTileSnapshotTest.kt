package com.imandroid.streambox.features.home.snapshot

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.imandroid.streambox.core.ui.components.TwoByThreeTile
import org.junit.Test

class TwoByThreeTileSnapshotTest(
    darkTheme: Boolean
) : LightDarkSnapshotTest(darkTheme) {

    @Test
    fun poster_tile_placeholder() {
        snapshot {
            Box(modifier = Modifier.size(width = 180.dp, height = 270.dp)) {
                TwoByThreeTile(
                    title = "Night Signal",
                    imageUrl = null
                )
            }
        }
    }
}
