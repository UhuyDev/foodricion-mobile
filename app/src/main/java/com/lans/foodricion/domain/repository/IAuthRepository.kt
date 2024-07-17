package com.lans.foodricion.domain.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Token
import kotlinx.coroutines.flow.Flow

interface IAuthRepository {
    suspend fun isAuthenticated(): Flow<Resource<Boolean>>
    suspend fun storeSession(userId: String, accessToken: String, refreshToken: String, expiredAt: Long)
    suspend fun signIn(email: String, password: String): Flow<Resource<Token>>
    suspend fun signUp(email: String, fullname: String, password: String): Flow<Resource<Boolean>>
    suspend fun signOut()
}