package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface ForgotPasswordUseCase {
    suspend fun invoke(email: String): Flow<Resource<Boolean>>
}