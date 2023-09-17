package mir.oslav.jet.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @since 1.0.1
 * @author Miroslav Hýbler <br>
 * created on 17.09.2023
 */
@RunWith(AndroidJUnit4::class)
class LuminanceTest constructor() {

    @Test
    fun isFullyLigtTest() {
        assertEquals(true, 0xFFFFFFFF.toInt().isFullyLightColor)
    }

    @Test
    fun isProbablyLightTest() {
        assertEquals(true, 0xFFFFFFFF.toInt().isProbablyLightColor)
    }

    @Test
    fun isProbablyDarkTest() {
        assertEquals(true, 0xFF000000.toInt().isProbablyDarkColor)
    }

    @Test
    fun isFullyDarkTest() {
        assertEquals(true, 0xFF000000.toInt().isFullyDarkColor)
    }
}