package com.lans.foodricion.presentation.screen.food_detail

import com.lans.foodricion.domain.model.Food

data class FoodDetailUIState(
    val isLoading: Boolean = false,
    var error: String = "",
    var food: Food? = null,
    var isDailyNutritionAdded: Boolean = false
)
