package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IUserRepository
import com.lans.foodricion.domain.usecase.UpdateProfileMetricsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UpdateProfileMetricsInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : UpdateProfileMetricsUseCase {
    override suspend fun invoke(age: Int, height: Int, weight: Int): Flow<Resource<Boolean>> {
        return userRepository.updateProfileMetrics(age, height, weight).flowOn(Dispatchers.IO)
    }
}