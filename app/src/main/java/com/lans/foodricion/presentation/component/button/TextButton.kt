package com.lans.foodricion.presentation.component.button

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import com.lans.foodricion.presentation.theme.Secondary

@Composable
fun TextButton(
    modifier: Modifier,
    text: String,
    isEnable: Boolean = true,
    color: Color = Secondary,
    fontSize: TextUnit,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Start,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .clickable {
                if (isEnable) {
                    onClick()
                }
            },
        text = text,
        color = if (isEnable) color else Color.Gray,
        fontSize = fontSize,
        fontWeight = fontWeight,
        textAlign = textAlign
    )
}