package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.Token
import com.squareup.moshi.Json

data class SignInResponseDto(
    @field:Json(name = "access_token")
    val accessToken: String,
    @field:Json(name = "refresh_token")
    val refreshToken: String,
    @field:Json(name = "expired_at")
    val expiredAt: Long
)

fun SignInResponseDto.toDomain() = Token(
    accessToken = accessToken,
    refreshToken = refreshToken,
    expiredAt = expiredAt
)
