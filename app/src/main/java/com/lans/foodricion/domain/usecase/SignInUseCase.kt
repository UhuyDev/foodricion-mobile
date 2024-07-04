package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface SignInUseCase {
    suspend fun invoke(email: String, password: String): Flow<Resource<Token>>
}