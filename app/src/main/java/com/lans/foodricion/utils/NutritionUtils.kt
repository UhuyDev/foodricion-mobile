package com.lans.foodricion.utils

data class MinimumNutrition(
    val calorie: Int,
    val protein: Int,
    val fiber: Int,
    val carbohydrate: Int,
    val fat: Int
)

fun getMinimumNutrition(age: Int): MinimumNutrition {
    return when {
        age in 10..12 -> MinimumNutrition(
            calorie = 1600,
            protein = 34,
            fiber = 22,
            carbohydrate = 130,
            fat = 50
        )

        age in 13..15 -> MinimumNutrition(
            calorie = 1800,
            protein = 46,
            fiber = 25,
            carbohydrate = 130,
            fat = 55
        )

        age in 16..18 -> MinimumNutrition(
            calorie = 2000,
            protein = 52,
            fiber = 25,
            carbohydrate = 130,
            fat = 60
        )

        age in 19..29 -> MinimumNutrition(
            calorie = 2000,
            protein = 46,
            fiber = 25,
            carbohydrate = 130,
            fat = 65
        )

        age in 30..49 -> MinimumNutrition(
            calorie = 1800,
            protein = 46,
            fiber = 25,
            carbohydrate = 130,
            fat = 65
        )

        age in 50..64 -> MinimumNutrition(
            calorie = 1600,
            protein = 46,
            fiber = 21,
            carbohydrate = 130,
            fat = 65
        )

        age in 65..80 -> MinimumNutrition(
            calorie = 1600,
            protein = 46,
            fiber = 21,
            carbohydrate = 130,
            fat = 60
        )

        age > 80 -> MinimumNutrition(
            calorie = 1400,
            protein = 46,
            fiber = 21,
            carbohydrate = 130,
            fat = 60
        )

        else -> throw IllegalArgumentException("Age is invalid")
    }
}