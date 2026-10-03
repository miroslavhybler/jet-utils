package com.jet.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PaddingValuesTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun plusRecalculatesWhenPaddingChanges() {
        val mutablePadding = MutablePaddingValues(initial = 1.dp)
        lateinit var combinedPadding: PaddingValues

        composeRule.setContent {
            combinedPadding = mutablePadding + PaddingValues(all = 2.dp)
        }

        composeRule.runOnIdle {
            assertEquals(3.dp, combinedPadding.calculateTopPadding())
            mutablePadding.value.value = 4.dp
        }

        composeRule.runOnIdle {
            assertEquals(6.dp, combinedPadding.calculateTopPadding())
        }
    }
}

private class MutablePaddingValues(initial: Dp) : PaddingValues {

    val value = mutableStateOf(initial)

    override fun calculateLeftPadding(layoutDirection: LayoutDirection): Dp = value.value

    override fun calculateTopPadding(): Dp = value.value

    override fun calculateRightPadding(layoutDirection: LayoutDirection): Dp = value.value

    override fun calculateBottomPadding(): Dp = value.value
}
