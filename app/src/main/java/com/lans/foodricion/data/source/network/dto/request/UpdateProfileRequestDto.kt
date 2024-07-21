package com.lans.foodricion.data.source.network.dto.request

import com.squareup.moshi.Json

data class UpdateProfileRequestDto(
    @field:Json(name = "full_name")
    val fullname: String,
    val email: String
)
