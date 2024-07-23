package com.lans.foodricion.data.source.network.dto.response

import com.lans.foodricion.domain.model.User
import java.util.Date

data class UpdateProfileMetricsResponseDto(
    val age: Int,
    val height: Int,
    val weight: Int
)
