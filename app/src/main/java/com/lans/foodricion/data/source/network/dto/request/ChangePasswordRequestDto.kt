package com.lans.foodricion.data.source.network.dto.request

import com.squareup.moshi.Json

data class ChangePasswordRequestDto(
    @field:Json(name = "old_password") val oldPassword: String,
    @field:Json(name = "new_password") val newPassword: String
)