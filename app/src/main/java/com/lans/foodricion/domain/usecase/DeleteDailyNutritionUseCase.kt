package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface DeleteDailyNutritionUseCase {
    suspend fun invoke(dailyNutritionId: Int): Flow<Resource<Boolean>>
}