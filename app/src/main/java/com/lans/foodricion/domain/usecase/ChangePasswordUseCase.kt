package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface ChangePasswordUseCase {
    suspend fun invoke(oldPassword: String, newPassword: String): Flow<Resource<Boolean>>
}