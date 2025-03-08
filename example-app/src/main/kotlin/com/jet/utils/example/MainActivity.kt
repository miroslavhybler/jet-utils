package com.jet.utils.example

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.jet.utils.plus
import com.jet.utils.theme.MaterialColorsPreview
import com.jet.utils.theme.MaterialTypography


/**
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val systemBarsStyle = SystemBarStyle.auto(
            Color.TRANSPARENT,
            Color.TRANSPARENT
        )
        enableEdgeToEdge(statusBarStyle = systemBarsStyle, navigationBarStyle = systemBarsStyle)
        setContent {
            val view = LocalView.current
            val contentPadding = remember { PaddingValues(top = 12.dp, bottom = 16.dp) }
            LaunchedEffect(key1 = Unit) {
                WindowCompat.setDecorFitsSystemWindows(this@MainActivity.window, false)
                WindowInsetsControllerCompat(this@MainActivity.window, view)
                    .show(WindowInsetsCompat.Type.systemBars())
            }

            JetUtilsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold { paddingValues ->
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(state = rememberScrollState())
                                .padding(horizontal = 12.dp)
                                .padding(paddingValues = paddingValues + contentPadding)
                        ) {
                            MaterialTypography()

                            MaterialColorsPreview()

                            ConfigurationsPreview()
                        }
                    }
                }
            }
        }
    }
}

