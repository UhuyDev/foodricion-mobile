package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IDailyNutritionRepository
import com.lans.foodricion.domain.usecase.DeleteDailyNutritionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteDailyNutritionInteractor @Inject constructor(
    private val dailyNutritionRepository: IDailyNutritionRepository
) : DeleteDailyNutritionUseCase {
    override suspend fun invoke(dailyNutritionId: Int): Flow<Resource<Boolean>> {
        return dailyNutritionRepository.deleteDailyNutrition(dailyNutritionId).flowOn(Dispatchers.IO)
    }
}