package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IAuthRepository
import com.lans.foodricion.domain.usecase.IsAuthenticatedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class IsAuthenticatedInteractor @Inject constructor(
    private val authRepository: IAuthRepository
) : IsAuthenticatedUseCase {
    override suspend fun invoke(): Flow<Resource<Boolean>> {
        return authRepository.isAuthenticated().flowOn(Dispatchers.IO)
    }
}