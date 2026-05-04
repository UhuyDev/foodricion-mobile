package com.lans.foodricion.data.source.network.dto.request

import com.squareup.moshi.Json

data class AddUserDailyNutritionRequestDto(
    @field:Json(name = "food_name")
    val foodName: String
)
