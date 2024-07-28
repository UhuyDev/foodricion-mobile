package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IDailyNutritionRepository
import com.lans.foodricion.domain.usecase.AddDailyNutritionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AddDailyNutritionInteractor @Inject constructor(
    private val IDailyNutritionRepository: IDailyNutritionRepository
) : AddDailyNutritionUseCase {
    override suspend fun invoke(foodName: String): Flow<Resource<Boolean>> {
        return IDailyNutritionRepository.addDailyNutrition(foodName).flowOn(Dispatchers.IO)
    }
}