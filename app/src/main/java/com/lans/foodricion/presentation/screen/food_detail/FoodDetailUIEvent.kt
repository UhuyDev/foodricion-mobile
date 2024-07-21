package com.lans.foodricion.presentation.screen.food_detail

sealed class FoodDetailUIEvent {
    object AddToDailyNutrition : FoodDetailUIEvent()
}