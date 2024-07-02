package com.lans.foodricion.presentation.component.daily_nutrition

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.Test
import com.lans.foodricion.presentation.theme.White
import com.lans.foodricion.utils.formatFloat

@Composable
fun CalorieCircularBar(
    modifier: Modifier,
    progressValue: Float = 0f,
    maxValue: Float = 10f,
) {
    val textMeasurer = rememberTextMeasurer()
    val drawTextStyle = TextStyle(
        fontSize = 14.sp,
        color = Black
    )
    val calorie = "${formatFloat(progressValue)}/${formatFloat(maxValue)}"
    val calorieValueTextLayout = remember(progressValue) {
        textMeasurer.measure(calorie, drawTextStyle)
    }
    val calorieTextTextLayout = remember(progressValue) {
        textMeasurer.measure("Cal", drawTextStyle)
    }

    Canvas(
        modifier = modifier
            .size(120.dp)
            .scale(scaleX = 1f, scaleY = -1f)
    ) {
        drawArc(
            color = White,
            startAngle = 15f,
            sweepAngle = 330f,
            useCenter = false,
            style = Stroke(14.dp.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )
        drawArc(
            color = Test,
            startAngle = 15f,
            sweepAngle = 360f * (progressValue / maxValue).coerceIn(0f, 1f),
            useCenter = false,
            style = Stroke(14.dp.toPx(), cap = StrokeCap.Round),
            size = Size(size.width, size.height)
        )

        scale(scaleX = 1f, scaleY = -1f) {
            drawText(
                textMeasurer = textMeasurer,
                text = calorie,
                style = drawTextStyle,
                topLeft = Offset(
                    x = center.x - calorieValueTextLayout.size.width / 2,
                    y = center.y / 1.15f - calorieValueTextLayout.size.height/ 2f,
                )

            )
            drawText(
                textMeasurer = textMeasurer,
                text = "Cal",
                style = drawTextStyle,
                topLeft = Offset(
                    x = center.x - calorieTextTextLayout.size.width / 2,
                    y = center.y / 0.85f - calorieValueTextLayout.size.height / 2f,
                )

            )
        }
    }
}