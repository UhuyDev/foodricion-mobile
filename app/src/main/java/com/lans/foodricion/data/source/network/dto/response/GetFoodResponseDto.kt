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
    var foodNutrition: Nutrition? = null
)

data class NutritionResponseDto(
    @field:Json(name = "energy") var energy: Double = 0.0,
    @field:Json(name = "total_fat") var totalFat: Double = 0.0,
    @field:Json(name = "saturated_fat") var saturatedFat: Double = 0.0,
    @field:Json(name = "polyunsaturated_fat") var polyunsaturatedFat: Double = 0.0,
    @field:Json(name = "sugar") var sugar: Double = 0.0,
    @field:Json(name = "vitamin_A") var vitaminA: Double = 0.0,
    @field:Json(name = "vitamin_B1") var vitaminB1: Double = 0.0,
    @field:Json(name = "vitamin_B2") var vitaminB2: Double = 0.0,
    @field:Json(name = "vitamin_B3") var vitaminB3: Double = 0.0,
    @field:Json(name = "vitamin_C") var vitaminC: Double = 0.0,
    @field:Json(name = "total_carbohydrate") var totalCarbohydrate: Double = 0.0,
    @field:Json(name = "protein") var protein: Double = 0.0,
    @field:Json(name = "dietary_fiber") var dietaryFiber: Double = 0.0,
    @field:Json(name = "calcium") var calcium: Double = 0.0,
    @field:Json(name = "phosphorus") var phosphorus: Double = 0.0,
    @field:Json(name = "sodium") var sodium: Double = 0.0,
    @field:Json(name = "potassium") var potassium: Double = 0.0,
    @field:Json(name = "copper") var copper: Double = 0.0,
    @field:Json(name = "iron") var iron: Double = 0.0,
    @field:Json(name = "zinc") var zinc: Double = 0.0,
    @field:Json(name = "b_carotene") var bCarotene: Double = 0.0,
    @field:Json(name = "total_carotene") var totalCarotene: Double = 0.0,
    @field:Json(name = "water") var water: Double = 0.0,
    @field:Json(name = "ash") var ash: Double = 0.0
)


fun GetFoodResponseDto.toDomain(): Food {
    val nutrition = foodNutrition?.let { nutritionDto ->
        Nutrition(
            energy = nutritionDto.energy,
            totalFat = nutritionDto.totalFat,
            saturatedFat = nutritionDto.saturatedFat,
            polyunsaturatedFat = nutritionDto.polyunsaturatedFat,
            sugar = nutritionDto.sugar,
            vitaminA = nutritionDto.vitaminA,
            vitaminB1 = nutritionDto.vitaminB1,
            vitaminB2 = nutritionDto.vitaminB2,
            vitaminB3 = nutritionDto.vitaminB3,
            vitaminC = nutritionDto.vitaminC,
            totalCarbohydrate = nutritionDto.totalCarbohydrate,
            protein = nutritionDto.protein,
            dietaryFiber = nutritionDto.dietaryFiber,
            calcium = nutritionDto.calcium,
            phosphorus = nutritionDto.phosphorus,
            sodium = nutritionDto.sodium,
            potassium = nutritionDto.potassium,
            copper = nutritionDto.copper,
            iron = nutritionDto.iron,
            zinc = nutritionDto.zinc,
            bCarotene = nutritionDto.bCarotene,
            totalCarotene = nutritionDto.totalCarotene,
            water = nutritionDto.water,
            ash = nutritionDto.ash
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
