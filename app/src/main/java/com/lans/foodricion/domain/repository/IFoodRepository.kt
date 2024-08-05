package com.lans.foodricion.domain.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface IFoodRepository {
    suspend fun getFood(foodName: String): Flow<Resource<Food>>
    suspend fun getFoods(): Flow<Resource<List<Food>>>
    suspend fun getFoodRecommendation(): Flow<Resource<List<Food>>>
}