package com.jet.utils.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialTypographyTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun displayLabelsUseDisplayFontSizes() {
        composeRule.setContent {
            MaterialTheme(
                typography = Typography(
                    displayMedium = TextStyle(fontSize = 37.sp),
                    displaySmall = TextStyle(fontSize = 31.sp),
                    headlineMedium = TextStyle(fontSize = 19.sp),
                    headlineSmall = TextStyle(fontSize = 17.sp),
                ),
            ) {
                MaterialTypographyPreview()
            }
        }

        composeRule.onNodeWithText("Display Medium (37", substring = true).assertExists()
        composeRule.onNodeWithText("Display Small (31", substring = true).assertExists()
    }
}
