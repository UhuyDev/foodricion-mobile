package com.lans.foodricion.data.source.network.dto.request

import retrofit2.http.Query

data class ForgotPasswordRequestDto(
    @Query("email") val email: String
)
