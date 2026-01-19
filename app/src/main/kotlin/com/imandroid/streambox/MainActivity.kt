package com.imandroid.streambox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.imandroid.streambox.core.designsystem.theme.StreamBoxTheme
import com.imandroid.streambox.features.home.ui.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main entry point Activity for StreamBox.
 *
 * ## Pattern
 * Uses a single-activity architecture with Compose Navigation.
 * The MainActivity hosts the NavHost and applies the app theme.
 *
 * ## Current State (main branch)
 * - Displays HomeScreen directly without navigation
 * - Navigation will be added in the `feature/navigation-flow` branch
 *
 * ## Responsibilities
 * - Apply StreamBoxTheme
 * - Enable edge-to-edge display
 * - Host the root composable
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            StreamBoxTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    HomeScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
