package mir.oslav.jet.utils.example

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import mir.oslav.jet.utils.isExtraLargeScreen
import mir.oslav.jet.utils.isLargeScreen
import mir.oslav.jet.utils.isNormalScreen
import mir.oslav.jet.utils.isSmallScreen
import mir.oslav.jet.utils.screenHeightPx
import mir.oslav.jet.utils.screenWidthPx


/**
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
fun Configurations() {

    val configuration = LocalConfiguration.current

    Spacer(modifier = Modifier.height(height = 24.dp))

    Text(
        text = "Screen size",
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.fillMaxWidth(),
    )

    Spacer(modifier = Modifier.height(height = 6.dp))


    ConfigurationRow(title = "Width Dp", value = "${configuration.screenWidthDp}")
    ConfigurationRow(title = "Height Dp", value = "${configuration.screenHeightDp}")


    ConfigurationRow(title = "Width Px", value = "${configuration.screenWidthPx}")
    ConfigurationRow(title = "Height Px", value = "${configuration.screenHeightPx}")

    ConfigurationRow(title = "Is XLarge", value = "${configuration.isExtraLargeScreen}")
    ConfigurationRow(title = "Is Large", value = "${configuration.isLargeScreen}")
    ConfigurationRow(title = "Is Normal", value = "${configuration.isNormalScreen}")
    ConfigurationRow(title = "Is Small", value = "${configuration.isSmallScreen}")

}

@Composable
private fun ConfigurationRow(
    modifier: Modifier = Modifier,
    title: String,
    value: String
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.weight(1f),
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.End
        )
    }
}