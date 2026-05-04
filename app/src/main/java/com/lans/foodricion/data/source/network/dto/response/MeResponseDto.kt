package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.User
import com.lans.foodricion.domain.model.UserMetric
import java.util.Date

data class MeResponseDto(
    val fullname: String,
    val email: String,
    val age: Int? = 0,
    val height: Int? = 0,
    val weight: Int? = 0
)

fun MeResponseDto.toDomain() = User(
    id = "",
    email = email,
    fullname = fullname,
    registrationDate = Date(),
    userMetric = UserMetric(
        age = age ?: 0,
        height = height ?: 0,
        weight = weight ?: 0
    )
)