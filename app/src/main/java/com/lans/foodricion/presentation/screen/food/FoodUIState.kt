package com.lans.foodricion.presentation.screen.food

import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.model.InputWrapper

data class FoodUIState(
    val search: InputWrapper = InputWrapper(),
    val isLoading: Boolean = false,
    var error: String = "",
    var foods: List<Food> = emptyList(),
    var foodsDefault: List<Food> = emptyList(),
    var isDailyNutritionAdded: Boolean = false
)
