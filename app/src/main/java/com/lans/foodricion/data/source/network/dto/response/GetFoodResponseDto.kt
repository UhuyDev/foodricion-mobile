package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.model.Nutrition
import com.squareup.moshi.Json

data class GetFoodResponseDto(
    @field:Json(name = "food_id")
    val id: Int,
    @field:Json(name = "food_name")
    val foodName: String,
    @field:Json(name = "food_image")
    val foodImage: String? = null,
    @field:Json(name = "food_type")
    val foodType: String? = null,
    @field:Json(name = "food_calories")
    val foodCalories: Double,
    @field:Json(name = "nutrition_details")
    var foodNutrition: NutritionResponseDto? = null
)

data class NutritionResponseDto(
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

fun GetFoodResponseDto.toDomain(): Food {
    val nutrition = foodNutrition?.let { nutritionDto ->
        Nutrition(
            energy = nutritionDto.energy ?: 0.0,
            totalFat = nutritionDto.totalFat ?: 0.0,
            saturatedFat = nutritionDto.saturatedFat ?: 0.0,
            polyunsaturatedFat = nutritionDto.polyunsaturatedFat ?: 0.0,
            sugar = nutritionDto.sugar ?: 0.0,
            vitaminA = nutritionDto.vitaminA ?: 0.0,
            vitaminB1 = nutritionDto.vitaminB1 ?: 0.0,
            vitaminB2 = nutritionDto.vitaminB2 ?: 0.0,
            vitaminB3 = nutritionDto.vitaminB3 ?: 0.0,
            vitaminC = nutritionDto.vitaminC ?: 0.0,
            totalCarbohydrate = nutritionDto.totalCarbohydrate ?: 0.0,
            protein = nutritionDto.protein ?: 0.0,
            dietaryFiber = nutritionDto.dietaryFiber ?: 0.0,
            calcium = nutritionDto.calcium ?: 0.0,
            phosphorus = nutritionDto.phosphorus ?: 0.0,
            sodium = nutritionDto.sodium ?: 0.0,
            potassium = nutritionDto.potassium ?: 0.0,
            copper = nutritionDto.copper ?: 0.0,
            iron = nutritionDto.iron ?: 0.0,
            zinc = nutritionDto.zinc ?: 0.0,
            bCarotene = nutritionDto.bCarotene ?: 0.0,
            totalCarotene = nutritionDto.totalCarotene ?: 0.0,
            water = nutritionDto.water ?: 0.0,
            ash = nutritionDto.ash ?: 0.0
        )
    } ?: Nutrition(
        energy = 0.0,
        totalFat = 0.0,
        saturatedFat = 0.0,
        polyunsaturatedFat = 0.0,
        sugar = 0.0,
        vitaminA = 0.0,
        vitaminB1 = 0.0,
        vitaminB2 = 0.0,
        vitaminB3 = 0.0,
        vitaminC = 0.0,
        totalCarbohydrate = 0.0,
        protein = 0.0,
        dietaryFiber = 0.0,
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
        id = id,
        foodName = foodName,
        foodImage = foodImage ?: "",
        foodType = foodType ?: "",
        foodCalories = foodCalories,
        foodNutrition = nutrition
    )
}
