package com.lans.foodricion.presentation.screen.food

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lans.foodricion.R
import com.lans.foodricion.presentation.component.alert.Alert
import com.lans.foodricion.presentation.component.food_item.FoodItem
import com.lans.foodricion.presentation.component.textfield.ValidableTextField
import com.lans.foodricion.presentation.theme.Background
import com.lans.foodricion.presentation.theme.Black
import com.lans.foodricion.presentation.theme.Primary

@Composable
fun FoodScreen(
    viewModel: FoodViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToFoodDetail: (foodName: String) -> Unit
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
        viewModel.getFoods()
    }

    LaunchedEffect(key1 = state.error) {
        val error = state.error

        if (error.isNotBlank()) {
            showAlert = Pair(true, state.error)
            state.error = ""
        }
    }

    Column(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
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
                        navigateToHome.invoke()
                    }
                    .align(Alignment.CenterStart),
                painter = painterResource(id = R.drawable.ic_back),
                tint = Black,
                contentDescription = stringResource(id = R.string.content_description)
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.foods),
                color = Black,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        ValidableTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            input = state.search,
            placeholder = "Search",
            isSupportiveText = false,
            trailingIcon = {
                IconButton(onClick = {

                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        tint = Black,
                        contentDescription = stringResource(id = R.string.content_description)
                    )
                }
            },
            onValueChange = {
                viewModel.onEvent(FoodUIEvent.SearchChanged(it))
            }
        )
        if (state.isLoading) {
            Column(
                modifier = Modifier
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp),
                    color = Primary
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 16.dp,
                        bottom = 16.dp,
                        end = 16.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.foods) { food ->
                    FoodItem(
                        modifier = Modifier,
                        imgUrl = food.foodImage,
                        foodName = food.foodName,
                        calorie = food.foodCalories.toInt(),
                        onClick = {
                            navigateToFoodDetail.invoke(food.foodName)
                        }
                    )
                }
            }
        }
    }
}