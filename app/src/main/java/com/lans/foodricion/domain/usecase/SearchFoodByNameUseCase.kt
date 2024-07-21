package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface SearchFoodByNameUseCase {
    suspend fun invoke(foodList: List<Food>, foodName: String): Flow<Resource<List<Food>>>
}