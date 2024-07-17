package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface GetFoodByNameUseCase {
    suspend fun invoke(foodName: String): Flow<Resource<Food>>
}