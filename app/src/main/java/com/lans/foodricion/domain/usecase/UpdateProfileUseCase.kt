package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface UpdateProfileUseCase {
    suspend fun invoke(fullname: String, email: String): Flow<Resource<Boolean>>
}