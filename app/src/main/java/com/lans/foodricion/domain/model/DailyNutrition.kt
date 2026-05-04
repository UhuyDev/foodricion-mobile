package com.lans.foodricion.domain.model

data class DailyNutrition(
    val dailyNutritionId: Int,
    val foodName: String,
    val foodImage: String,
    val foodNutrition: Nutrition
)
