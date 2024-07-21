package com.lans.foodricion.domain.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun me(): Flow<Resource<User>>
    suspend fun changePassword(oldPassword: String, newPassword: String): Flow<Resource<Boolean>>
    suspend fun forgotPassword(email: String): Flow<Resource<Boolean>>
    suspend fun updateProfile(fullname: String, email: String): Flow<Resource<Boolean>>
    suspend fun verifyOTP(email: String, otp: String, newPassword: String): Flow<Resource<Boolean>>
}