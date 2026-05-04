package com.lans.foodricion.presentation.screen.food

sealed class FoodUIEvent {
    data class SearchChanged(val search: String): FoodUIEvent()
}