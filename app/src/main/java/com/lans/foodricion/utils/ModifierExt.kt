package com.lans.foodricion.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

fun Modifier.topBorder(strokeWidth: Dp, color: Color) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }

        Modifier.drawBehind {
            drawLine(
                color = color,
                start = Offset(x = 0f, y = strokeWidthPx/2),
                end = Offset(x = size.width , y = strokeWidthPx/2),
                strokeWidth = strokeWidthPx
            )
        }
    }
)