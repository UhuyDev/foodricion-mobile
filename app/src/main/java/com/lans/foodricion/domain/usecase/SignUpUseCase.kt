package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface SignUpUseCase {
    suspend fun invoke(email: String, fullname: String, password: String): Flow<Resource<Boolean>>
}