package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.User
import kotlinx.coroutines.flow.Flow

interface GetMeUseCase {
    suspend fun invoke(): Flow<Resource<User>>
}