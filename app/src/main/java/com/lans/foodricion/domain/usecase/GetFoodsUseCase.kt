package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface GetFoodsUseCase {
    suspend fun invoke(): Flow<Resource<List<Food>>>
}