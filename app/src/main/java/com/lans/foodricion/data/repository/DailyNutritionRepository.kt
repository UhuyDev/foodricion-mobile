package com.lans.foodricion.data.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.data.source.network.SafeApiCall
import com.lans.foodricion.data.source.network.api.FoodricionApi
import com.lans.foodricion.data.source.network.dto.request.AddUserDailyNutritionRequestDto
import com.lans.foodricion.data.source.network.dto.request.DeleteUserDailyNutritionRequestDto
import com.lans.foodricion.data.source.network.dto.response.toDomain
import com.lans.foodricion.domain.model.DailyNutrition
import com.lans.foodricion.domain.repository.IDailyNutritionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DailyNutritionRepository @Inject constructor(
    private val api: FoodricionApi
) : IDailyNutritionRepository, SafeApiCall {
    override suspend fun getDailyNutritions(): Flow<Resource<List<DailyNutrition>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.getDailyNutritions()
                if (response.code == 200) {
                    response.data?.map { dailyNutrition ->
                        dailyNutrition.toDomain()
                    } ?: emptyList()
                } else {
                    throw Exception("Something went wrong")
                }
            })
        }
    }

    override suspend fun addDailyNutrition(foodName: String): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.addDailyNutrition(
                    AddUserDailyNutritionRequestDto(
                        foodName = foodName
                    )
                ).code == 200
            })
        }
    }

    override suspend fun deleteDailyNutrition(dailyNutritionId: Int): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.deleteDailyNutrition(
                    DeleteUserDailyNutritionRequestDto(
                        bookmarkId = dailyNutritionId
                    )
                ).code == 200
            })
        }
    }
}