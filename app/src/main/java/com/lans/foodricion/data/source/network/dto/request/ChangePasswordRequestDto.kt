package com.lans.foodricion.data.source.network.dto.request

import retrofit2.http.Query

data class ChangePasswordRequestDto (
    val oldPassword: String,
    val newPassword: String
)