package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.model.Nutrition
import com.squareup.moshi.Json

data class GetFoodRecommendationResponseDto(
    @field:Json(name = "food_name")
    val foodName: String,
    @field:Json(name = "food_image")
    val foodImage: String? = null,
    @field:Json(name = "kalori")
    val calorie: Double? = null,
    @field:Json(name = "protein")
    val protein: Double? = null,
    @field:Json(name = "serat")
    val fiber: Double? = null,
    @field:Json(name = "karbohidrat")
    val carbohydrate: Double? = null,
    @field:Json(name = "lemak")
    val fat: Double? = null
)

fun GetFoodRecommendationResponseDto.toDomain(): Food {
    val nutrition = Nutrition(
        energy = calorie ?: 0.0,
        totalFat = 0.0,
        saturatedFat = 0.0,
        polyunsaturatedFat = 0.0,
        sugar = 0.0,
        vitaminA = 0.0,
        vitaminB1 = 0.0,
        vitaminB2 = 0.0,
        vitaminB3 = 0.0,
        vitaminC = 0.0,
        totalCarbohydrate = carbohydrate ?: 0.0,
        protein = protein ?: 0.0,
        dietaryFiber = fiber ?: 0.0,
        calcium = 0.0,
        phosphorus = 0.0,
        sodium = 0.0,
        potassium = 0.0,
        copper = 0.0,
        iron = 0.0,
        zinc = 0.0,
        bCarotene = 0.0,
        totalCarotene = 0.0,
        water = 0.0,
        ash = 0.0
    )

    return Food(
        id = 0,
        foodName = foodName,
        foodImage = foodImage ?: "",
        foodType = "",
        foodCalories = calorie ?: 0.0,
        foodNutrition = nutrition
    )
}
