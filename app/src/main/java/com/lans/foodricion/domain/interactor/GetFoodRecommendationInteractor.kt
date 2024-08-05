package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.repository.IFoodRepository
import com.lans.foodricion.domain.usecase.GetRecommendationFoodUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFoodRecommendationInteractor @Inject constructor(
    private val foodRepository: IFoodRepository
) : GetRecommendationFoodUseCase {
    override suspend fun invoke(): Flow<Resource<List<Food>>> {
        return foodRepository.getFoodRecommendation().flowOn(Dispatchers.IO)
    }
}