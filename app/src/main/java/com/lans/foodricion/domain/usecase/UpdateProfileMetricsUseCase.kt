package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.UserMetric
import kotlinx.coroutines.flow.Flow

interface UpdateProfileMetricsUseCase {
    suspend fun invoke(age: Int, height: Int, weight: Int): Flow<Resource<Boolean>>
}