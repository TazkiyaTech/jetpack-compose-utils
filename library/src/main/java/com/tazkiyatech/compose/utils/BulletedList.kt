package com.tazkiyatech.compose.utils

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Displays the given array of Strings as a bulleted list.
 *
 * @param bullets The array of Strings to display as a bulleted list.
 * @param modifier The [Modifier] to apply to this view.
 * @param textAlign The [TextAlign] to apply to the text in this view.
 * @param textStyle The [TextStyle] to apply to the text in this view.
 * @param verticalSpaceBetweenBullets The amount of vertical space to place between bullets.
 */
@Composable
fun BulletedList(bullets: Array<String>,
                 modifier: Modifier = Modifier,
                 textAlign: TextAlign? = null,
                 textStyle: TextStyle = LocalTextStyle.current,
                 verticalSpaceBetweenBullets: Dp = 0.dp) {
    Column(verticalArrangement = spacedBy(verticalSpaceBetweenBullets), modifier = modifier) {
        bullets.forEach { BulletedText(it, textAlign, textStyle) }
    }
}

@Preview(showBackground = true)
@Composable
fun BulletedListPreview() {
    val bullets = arrayOf(
        "This is a long piece of text which will span over to a second line and will indent to the right of the bullet point.",
        "This is another long piece of text which will span over to a second line and will indent to the right of the bullet point."
    )

    MaterialTheme {
        BulletedList(bullets)
    }
}

@Composable
private fun BulletedText(text: String, textAlign: TextAlign?, textStyle: TextStyle) {
    Row {
        Text(text = "\u2022 ", textAlign = textAlign, style = textStyle)
        Text(text = text, textAlign = textAlign, style = textStyle)
    }
}
