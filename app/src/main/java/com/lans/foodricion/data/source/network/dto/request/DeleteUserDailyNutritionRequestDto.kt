package com.lans.foodricion.data.source.network.dto.request

import com.squareup.moshi.Json

data class DeleteUserDailyNutritionRequestDto(
    @field:Json(name = "bookmark_id")
    val bookmarkId: Int
)
