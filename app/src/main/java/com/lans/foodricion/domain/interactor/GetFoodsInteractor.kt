package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.repository.IFoodRepository
import com.lans.foodricion.domain.usecase.GetFoodsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetFoodsInteractor @Inject constructor(
    private val foodRepository: IFoodRepository
) : GetFoodsUseCase {
    override suspend fun invoke(): Flow<Resource<List<Food>>> {
        return foodRepository.getFoods().flowOn(Dispatchers.IO)
    }
}