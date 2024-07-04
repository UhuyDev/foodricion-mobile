package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface VerifyOTPUseCase {
    suspend fun invoke(email: String, otp: String, newPassword: String): Flow<Resource<Boolean>>
}