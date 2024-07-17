package com.lans.foodricion.data.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.data.source.network.SafeApiCall
import com.lans.foodricion.data.source.network.api.FoodricionApi
import com.lans.foodricion.data.source.network.dto.response.toDomain
import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.repository.IFoodRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val api: FoodricionApi
) : IFoodRepository, SafeApiCall {
    override suspend fun getFood(foodName: String): Flow<Resource<Food>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getFood(foodName)
                if (response.code == 200) {
                    response.data!!.toDomain()
                } else {
                    throw Exception("Food not found")
                }
            })
        }
    }

    override suspend fun getFoods(): Flow<Resource<List<Food>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getFoods()
                if (response.code == 200) {
                    response.data?.map { food ->
                        food.toDomain()
                    } ?: emptyList()
                } else {
                    throw Exception("Something went wrong")
                }
            })
        }
    }
}