package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.DailyNutrition
import kotlinx.coroutines.flow.Flow

interface GetDailyNutritionsUseCase {
    suspend fun invoke(): Flow<Resource<List<DailyNutrition>>>
}