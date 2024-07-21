package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.usecase.SearchFoodByNameUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchFoodByNameInteractor : SearchFoodByNameUseCase {
    override suspend fun invoke(
        foodList: List<Food>,
        foodName: String
    ): Flow<Resource<List<Food>>> {
        return flow {
            try {
                val data = foodList.filter {
                    it.foodName.contains(foodName, ignoreCase = true)
                }
                emit(Resource.Success(data))
            } catch (ex: Exception) {
                emit(Resource.Error(ex.message.toString()))
            }
        }
    }
}