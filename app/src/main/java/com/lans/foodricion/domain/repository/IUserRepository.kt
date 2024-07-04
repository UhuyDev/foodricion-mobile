package com.lans.foodricion.domain.repository

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun forgotPassword(email: String): Flow<Resource<Boolean>>
    suspend fun verifyOTP(email: String, otp: String, newPassword: String): Flow<Resource<Boolean>>
}