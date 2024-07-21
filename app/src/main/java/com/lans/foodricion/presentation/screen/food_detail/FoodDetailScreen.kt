package com.lans.foodricion.presentation.screen.food_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.alert.Alert
import com.lans.foodricion.presentation.component.button.LoadingButton
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Neutral
import com.lans.foodricion.presentation.theme.RoundedLarge
import com.lans.foodricion.presentation.theme.RoundedMedium
import com.lans.foodricion.presentation.theme.White

@Composable
fun FoodDetailScreen(
    viewModel: FoodDetailViewModel = hiltViewModel(),
    navigateToFood: () -> Unit,
    foodName: String
) {
    val state by viewModel.state
    var showAlert by remember { mutableStateOf(Pair(false, "")) }

    if (showAlert.first) {
        Alert(
            title = "Error",
            description = showAlert.second,
            onDismissClick = {
                showAlert = showAlert.copy(first = false)
            },
            onConfirmClick = {
                Button(onClick = {
                    showAlert = showAlert.copy(first = false)
                }) {
                    Text(text = "Close")
                }
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.getFood(foodName)
    }

    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(48.dp)
                    .padding(
                        start = 16.dp
                    )
                    .clickable {
                        navigateToFood.invoke()
                    }
                    .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.ic_back),
                tint = Black,
                contentDescription = stringResource(id = R.string.content_description)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = foodName,
                color = Black,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        AsyncImage(
            modifier = Modifier
                .background(
                    color = White,
                    shape = RoundedMedium
                )
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedLarge),
            model = state.food?.foodImage,
            error = painterResource(id = R.drawable.img_food),
            contentDescription = stringResource(R.string.content_description)
        )
        state.food?.let { food ->
            val foodNutrition = food.foodNutrition

            val nutrients = listOf(
                "Protein" to Pair(foodNutrition.protein, "g"),
                "Carbohydrate" to Pair(foodNutrition.totalCarbohydrate, "g"),
                "Dietary Fiber" to Pair(foodNutrition.dietaryFiber, "g"),
                "Fat" to Pair(foodNutrition.totalFat, "g"),
                "Vitamin C" to Pair(foodNutrition.vitaminC, "mg"),
                "Vitamin A" to Pair(foodNutrition.vitaminA, "IU"),
                "Calcium" to Pair(foodNutrition.calcium, "mg"),
                "Iron" to Pair(foodNutrition.iron, "mg"),
                "Potassium" to Pair(foodNutrition.potassium, "mg"),
                "Vitamin B1" to Pair(foodNutrition.vitaminB1, "mg"),
                "Vitamin B2" to Pair(foodNutrition.vitaminB2, "mg"),
                "Vitamin B3" to Pair(foodNutrition.vitaminB3, "mg"),
                "Saturated Fat" to Pair(foodNutrition.saturatedFat, "g"),
                "Polyunsaturated Fat" to Pair(foodNutrition.polyunsaturatedFat, "g"),
                "Phosphorus" to Pair(foodNutrition.phosphorus, "mg"),
                "Sodium" to Pair(foodNutrition.sodium, "mg"),
                "Zinc" to Pair(foodNutrition.zinc, "mg"),
                "Copper" to Pair(foodNutrition.copper, "mg"),
                "b-Carotene" to Pair(foodNutrition.bCarotene, "µg"),
                "Carotene" to Pair(foodNutrition.totalCarotene, "µg"),
                "Sugar" to Pair(foodNutrition.sugar, "g"),
                "Water" to Pair(foodNutrition.water, "g"),
                "Ash" to Pair(foodNutrition.ash, "g"),
                "Energy" to Pair(foodNutrition.energy, "kcal")
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        start = 16.dp,
                    ),
                text = stringResource(R.string.nutritions),
                color = Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 16.dp,
                        vertical = 8.dp
                    )
            ) {
                nutrients.forEach { (label, value) ->
                    if (value.first != 0.0) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    vertical = 8.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = label,
                                color = Black
                            )
                            Text(
                                text = "${value.first} ${value.second}",
                                color = Black
                            )
                        }
                    }
                }
            }
            LoadingButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .padding(
                        horizontal = 16.dp
                    ),
                text = stringResource(R.string.add_to_daily_nutrition),
                isLoading = false,
                onClick = {
                    viewModel.onEvent(FoodDetailUIEvent.AddToDailyNutrition)
                }
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
            )
        } ?: run {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.no_food_data_available),
                    color = Neutral
                )
            }
        }
    }
}