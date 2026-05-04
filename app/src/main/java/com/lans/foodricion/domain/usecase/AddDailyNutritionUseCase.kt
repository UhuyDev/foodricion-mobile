package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface AddDailyNutritionUseCase {
    suspend fun invoke(foodName: String): Flow<Resource<Boolean>>
}