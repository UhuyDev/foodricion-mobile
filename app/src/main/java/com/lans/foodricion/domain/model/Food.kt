package com.lans.foodricion.domain.model

data class Food(
    val id: Int,
    val foodName: String,
    val foodImage: String,
    val foodType: String,
    val foodCalories: Double,
    val foodNutrition: Nutrition
)
