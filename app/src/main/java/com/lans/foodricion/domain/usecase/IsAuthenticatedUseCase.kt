package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface IsAuthenticatedUseCase {
    suspend fun invoke(): Flow<Resource<Boolean>>
}