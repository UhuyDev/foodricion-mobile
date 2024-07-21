package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.User
import java.util.Date

data class UpdateProfileResponseDto(
    val fullname: String,
    val email: String
)
