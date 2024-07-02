package com.lans.foodricion.data.source.network.dto.request

import retrofit2.http.Query

data class SignUpRequestDto(
    @Query("email") val email: String,
    @Query("fullname") val fullname: String,
    @Query("password") val password: String
)