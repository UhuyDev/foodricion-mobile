package com.lans.foodricion.presentation.component.button

import androidx.compose.foundation.background
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.Secondary

@Composable
fun GhostButton(
    modifier: Modifier,
    text: String,
    contentColor: Color = Secondary,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedMedium,
        colors = ButtonDefaults
            .buttonColors(
                containerColor = Color.Transparent,
                contentColor = contentColor
            ),
        onClick = onClick
    ) {
        Text(text)
    }
}