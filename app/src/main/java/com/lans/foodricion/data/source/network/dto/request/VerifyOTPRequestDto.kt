package com.lans.foodricion.data.source.network.dto.request

import com.squareup.moshi.Json
import retrofit2.http.Query

data class VerifyOTPRequestDto(
    val email: String,
    val otp: String,
    @Json(name = "new_password") val newPassword: String
)
