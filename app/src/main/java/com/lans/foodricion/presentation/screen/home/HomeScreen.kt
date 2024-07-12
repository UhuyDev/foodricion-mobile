package com.lans.foodricion.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.button.CardButton
import com.lans.foodricion.presentation.component.daily_nutrition.DailyNutrition
import com.lans.foodricion.presentation.component.nutrition_history.NutritionHistoryItem
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Primary
import com.lans.foodricion.presentation.theme.PrimaryContainer

@Composable
fun HomeScreen(
    innerPadding: PaddingValues
) {
    Column(
        modifier = Modifier
            .background(Background)
            .padding(innerPadding)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 24.dp
                ),
            text = stringResource(R.string.welcome_user),
            color = Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        DailyNutrition(
            modifier = Modifier,
            calorieValue = 200f,
            calorieMaxValue = 1800f,
            proteinValue = 200f,
            proteinMaxValue = 1800f,
            carboValue = 200f,
            carboMaxValue = 1800f,
            fiberValue = 200f,
            fiberMaxValue = 1800f,
            fatValue = 200f,
            fatMaxValue = 1800f
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 24.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardButton(
                modifier = Modifier,
                icon = painterResource(id = R.drawable.ic_scan),
                iconColor = Primary,
                text = stringResource(R.string.scan),
                onClick = { }
            )
            CardButton(
                modifier = Modifier,
                icon = painterResource(id = R.drawable.ic_chat),
                iconColor = Primary,
                text = stringResource(R.string.foods),
                onClick = { }
            )
            CardButton(
                modifier = Modifier,
                icon = painterResource(id = R.drawable.ic_calculator),
                iconColor = Primary,
                text = stringResource(R.string.bmi),
                onClick = { }
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 16.dp
                )
                .weight(1f),
            colors = CardDefaults.cardColors(
                containerColor = PrimaryContainer
            )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        bottom = 12.dp
                    ),
                text = stringResource(R.string._29_jan_2024),
                color = Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
                NutritionHistoryItem(
                    imgUrl = "",
                    calorie = 220,
                    onClick = { }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        innerPadding = PaddingValues(0.dp)
    )
}
