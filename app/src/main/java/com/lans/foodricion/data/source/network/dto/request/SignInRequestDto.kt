package com.lans.foodricion.data.source.network.dto.request

import retrofit2.http.Query

data class SignInRequestDto(
    val email: String,
    val password: String
)
