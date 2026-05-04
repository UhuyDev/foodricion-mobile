package com.lans.foodricion.data.source.network.dto.request

import com.squareup.moshi.Json

data class VerifyOTPRequestDto(
    val email: String,
    val otp: String,
    @field:Json(name = "new_password")
    val newPassword: String
)
