package com.lans.foodricion.data.source.network.dto.response

import com.squareup.moshi.Json

data class RefreshTokenResponseDto(
    @field:Json(name = "access_token")
    val accessToken: String,
    @field:Json(name = "refresh_token")
    val refreshToken: String,
    @field:Json(name = "expired_at")
    val expiredAt: Long
)
