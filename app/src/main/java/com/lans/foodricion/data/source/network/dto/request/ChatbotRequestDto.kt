package com.lans.foodricion.data.source.network.dto.request

import com.squareup.moshi.Json

data class ChatbotRequestDto(
    @field:Json(name = "user_input")
    val message: String
)
