package com.lans.foodricion.domain.model

data class Token(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: Long
)