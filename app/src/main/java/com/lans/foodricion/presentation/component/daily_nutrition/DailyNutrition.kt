package com.lans.foodricion.presentation.component.daily_nutrition

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.PrimaryContainer

@Composable
fun DailyNutrition(
    modifier: Modifier,
    containerColor: Color = PrimaryContainer,
    textColor: Color = Black,
    calorieValue: Float,
    calorieMaxValue: Float,
    proteinValue: Float,
    proteinMaxValue: Float,
    carboValue: Float,
    carboMaxValue: Float,
    fiberValue: Float,
    fiberMaxValue: Float,
    fatValue: Float,
    fatMaxValue: Float,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(
                horizontal = 24.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 16.dp,
                    start = 16.dp,
                ),
            text = stringResource(R.string.daily_nutrition),
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    top = 16.dp,
                    bottom = 24.dp,
                    end = 20.dp
                ),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CalorieCircularBar(
                modifier = Modifier,
                progressValue = calorieValue,
                maxValue = calorieMaxValue
            )
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                NutritionBar(
                    text = stringResource(R.string.protein),
                    progressValue = proteinValue,
                    maxValue = proteinMaxValue
                )
                NutritionBar(
                    text = stringResource(R.string.carbo),
                    progressValue = carboValue,
                    maxValue = carboMaxValue
                )
                NutritionBar(
                    text = stringResource(R.string.fiber),
                    progressValue = fiberValue,
                    maxValue = fiberMaxValue
                )
                NutritionBar(
                    text = stringResource(R.string.fat),
                    progressValue = fatValue,
                    maxValue = fatMaxValue
                )
            }
        }
    }
}