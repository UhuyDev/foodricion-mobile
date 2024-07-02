package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.Token
import com.squareup.moshi.Json

data class SignInResponseDto(
    @field:Json(name = "status")
    val status: Int,
    @field:Json(name = "message")
    val message: String,
    @field:Json(name = "access_token")
    val accessToken: String,
    @field:Json(name = "refresh_token")
    val refreshToken: String,
    @field:Json(name = "expires")
    val expires: Long
)

fun SignInResponseDto.toDomain() = Token(
    accessToken = accessToken,
    refreshToken = refreshToken,
    expiredAt = expires
)
