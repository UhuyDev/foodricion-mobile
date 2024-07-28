package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.DailyNutrition
import com.lans.foodricion.domain.model.Nutrition
import com.squareup.moshi.Json

data class UserDailyNutritionResponseDto(
    @field:Json(name = "bookmark") var dailyNutritionId: Int,
    @field:Json(name = "food") var foodName: String,
    @field:Json(name = "food_image") var foodImage: String,
    @field:Json(name = "energy") var energy: Double? = null,
    @field:Json(name = "total_fat") var totalFat: Double? = null,
    @field:Json(name = "saturated_fat") var saturatedFat: Double? = null,
    @field:Json(name = "polyunsaturated_fat") var polyunsaturatedFat: Double? = null,
    @field:Json(name = "sugar") var sugar: Double? = null,
    @field:Json(name = "vitamin_A") var vitaminA: Double? = null,
    @field:Json(name = "vitamin_B1") var vitaminB1: Double? = null,
    @field:Json(name = "vitamin_B2") var vitaminB2: Double? = null,
    @field:Json(name = "vitamin_B3") var vitaminB3: Double? = null,
    @field:Json(name = "vitamin_C") var vitaminC: Double? = null,
    @field:Json(name = "total_carbohydrate") var totalCarbohydrate: Double? = null,
    @field:Json(name = "protein") var protein: Double? = null,
    @field:Json(name = "dietary_fiber") var dietaryFiber: Double? = null,
    @field:Json(name = "calcium") var calcium: Double? = null,
    @field:Json(name = "phosphorus") var phosphorus: Double? = null,
    @field:Json(name = "sodium") var sodium: Double? = null,
    @field:Json(name = "potassium") var potassium: Double? = null,
    @field:Json(name = "copper") var copper: Double? = null,
    @field:Json(name = "iron") var iron: Double? = null,
    @field:Json(name = "zinc") var zinc: Double? = null,
    @field:Json(name = "b_carotene") var bCarotene: Double? = null,
    @field:Json(name = "total_carotene") var totalCarotene: Double? = null,
    @field:Json(name = "water") var water: Double? = null,
    @field:Json(name = "ash") var ash: Double? = null
)

fun UserDailyNutritionResponseDto.toDomain(): DailyNutrition {
    val nutrition = Nutrition(
        energy = energy ?: 0.0,
        totalFat = totalFat ?: 0.0,
        saturatedFat = saturatedFat ?: 0.0,
        polyunsaturatedFat = polyunsaturatedFat ?: 0.0,
        sugar = sugar ?: 0.0,
        vitaminA = vitaminA ?: 0.0,
        vitaminB1 = vitaminB1 ?: 0.0,
        vitaminB2 = vitaminB2 ?: 0.0,
        vitaminB3 = vitaminB3 ?: 0.0,
        vitaminC = vitaminC ?: 0.0,
        totalCarbohydrate = totalCarbohydrate ?: 0.0,
        protein = protein ?: 0.0,
        dietaryFiber = dietaryFiber ?: 0.0,
        calcium = calcium ?: 0.0,
        phosphorus = phosphorus ?: 0.0,
        sodium = sodium ?: 0.0,
        potassium = potassium ?: 0.0,
        copper = copper ?: 0.0,
        iron = iron ?: 0.0,
        zinc = zinc ?: 0.0,
        bCarotene = bCarotene ?: 0.0,
        totalCarotene = totalCarotene ?: 0.0,
        water = water ?: 0.0,
        ash = ash ?: 0.0
    )

    return DailyNutrition(
        dailyNutritionId = dailyNutritionId,
        foodName = foodName,
        foodImage = foodImage,
        foodNutrition = nutrition
    )
}
