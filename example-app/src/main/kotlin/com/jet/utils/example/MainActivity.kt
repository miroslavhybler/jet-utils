package com.jet.utils.example

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

/**
 * Hosts the Navigation 3 powered showcase for the `jet-utils` library.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val transparentSystemBars = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT)
        enableEdgeToEdge(
            statusBarStyle = transparentSystemBars,
            navigationBarStyle = transparentSystemBars,
        )

        setContent {
            JetUtilsTheme {
                JetUtilsShowcaseApp()
            }
        }
    }
}
