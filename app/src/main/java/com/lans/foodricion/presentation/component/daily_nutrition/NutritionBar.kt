package com.lans.foodricion.presentation.component.daily_nutrition

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.utils.formatFloat

@Composable
fun NutritionBar(
    text: String,
    progressValue: Float = 0f,
    maxValue: Float = 10f,
    textColor: Color = Black,
    filledColor: Color = Primary
) {
    Column {
        Text(
            color = textColor,
            fontSize = 14.sp,
            text = text
        )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LinearProgressIndicator(
                progress = {
                    if (maxValue != 0f) {
                        (progressValue / maxValue).coerceIn(0f, 1f)
                    } else {
                        0f
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .clip(RoundedMedium),
                color = filledColor,
                trackColor = Color.White,
            )

            Text(
                text = "${formatFloat(progressValue)}/${formatFloat(maxValue)}",
                color = Black,
                textAlign = TextAlign.Center,
                fontSize = 12.sp
            )
        }
    }
}