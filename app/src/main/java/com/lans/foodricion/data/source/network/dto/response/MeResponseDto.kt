package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.User
import java.util.Date

data class MeResponseDto(
    val fullname: String,
    val email: String
)

fun MeResponseDto.toDomain() = User(
    id = "",
    email = email,
    fullname = fullname,
    registrationDate = Date()
)