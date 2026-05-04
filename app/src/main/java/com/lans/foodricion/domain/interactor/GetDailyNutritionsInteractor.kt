package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.DailyNutrition
import com.lans.foodricion.domain.repository.IDailyNutritionRepository
import com.lans.foodricion.domain.usecase.GetDailyNutritionsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetDailyNutritionsInteractor @Inject constructor(
    private val dailyNutritionRepository: IDailyNutritionRepository
) : GetDailyNutritionsUseCase {
    override suspend fun invoke(): Flow<Resource<List<DailyNutrition>>> {
        return dailyNutritionRepository.getDailyNutritions().flowOn(Dispatchers.IO)
    }
}