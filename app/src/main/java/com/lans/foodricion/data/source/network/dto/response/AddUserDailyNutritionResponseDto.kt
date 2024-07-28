package com.lans.foodricion.data.source.network.dto.response

import com.squareup.moshi.Json

data class AddUserDailyNutritionResponseDto(
    @field:Json(name = "bookmark_id")
    val bookmarkId: Int,
    @field:Json(name = "food_id")
    val foodId: Int,
    @field:Json(name = "bookmark_date")
    val bookmarkDate: String
)
