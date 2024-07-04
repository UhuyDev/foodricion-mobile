package com.lans.foodricion.data.source.network.dto

import com.squareup.moshi.Json

data class ApiResponse<T>(
    @field:Json(name = "code")
    val code: Int,
    @field:Json(name = "message")
    val message: String?,
    @field:Json(name = "data")
    val data: T?
)
