package com.lans.foodricion.data.source.network.dto.request

import retrofit2.http.Query

data class SignUpRequestDto(
    val email: String,
    val fullname: String,
    val password: String
)