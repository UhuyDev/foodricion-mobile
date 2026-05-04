package com.lans.foodricion.domain.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.DailyNutrition
import kotlinx.coroutines.flow.Flow

interface IDailyNutritionRepository {
    suspend fun getDailyNutritions(): Flow<Resource<List<DailyNutrition>>>
    suspend fun addDailyNutrition(foodName: String): Flow<Resource<Boolean>>
    suspend fun deleteDailyNutrition(dailyNutritionId: Int): Flow<Resource<Boolean>>
}