package mir.oslav.jet.utils.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


/**
 * @since 1.0.0
 * @author Miroslav Hýbler <br>
 * created on 01.09.2023
 */
@Composable
fun MaterialTypography() {
    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = "Display Large (${MaterialTheme.typography.displayLarge.fontSize})",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "Display Medium (${MaterialTheme.typography.headlineMedium.fontSize})",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "Display Small (${MaterialTheme.typography.headlineSmall.fontSize})",
            style = MaterialTheme.typography.displayMedium
        )


        Text(
            text = "Headline Large (${MaterialTheme.typography.headlineLarge.fontSize})",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "Headline Medium (${MaterialTheme.typography.headlineMedium.fontSize})",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Headline Small (${MaterialTheme.typography.headlineSmall.fontSize})",
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = "Title Large (${MaterialTheme.typography.titleLarge.fontSize})",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "Title Medium (${MaterialTheme.typography.titleMedium.fontSize})",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "Title Small (${MaterialTheme.typography.titleSmall.fontSize})",
            style = MaterialTheme.typography.titleSmall
        )


        Text(
            text = "Body Large (${MaterialTheme.typography.bodyLarge.fontSize})",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "Body Medium (${MaterialTheme.typography.bodyMedium.fontSize})",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "Body Small (${MaterialTheme.typography.bodySmall.fontSize})",
            style = MaterialTheme.typography.bodySmall
        )


        Text(
            text = "Label Large (${MaterialTheme.typography.labelLarge.fontSize})",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "Label Medium (${MaterialTheme.typography.labelMedium.fontSize})",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "Label Small (${MaterialTheme.typography.labelSmall.fontSize})",
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
@Preview
private fun MaterialTypographyPreview() {
    MaterialTypography()
}