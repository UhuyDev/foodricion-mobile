package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.repository.IFoodRepository
import com.lans.foodricion.domain.usecase.GetFoodByNameUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFoodByNameInteractor @Inject constructor(
    private val foodRepository: IFoodRepository
) : GetFoodByNameUseCase {
    override suspend fun invoke(foodName: String): Flow<Resource<Food>> {
        return foodRepository.getFood(foodName).flowOn(Dispatchers.IO)
    }
}