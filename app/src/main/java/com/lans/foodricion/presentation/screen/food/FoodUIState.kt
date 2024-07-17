package com.lans.foodricion.presentation.screen.food

import com.lans.foodricion.domain.model.Food

data class FoodUIState(
    val isLoading: Boolean = false,
    var error: String = "",
    var foods: List<Food> = emptyList()
)
