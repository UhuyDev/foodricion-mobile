package com.lans.foodricion.data.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.data.source.local.DataStoreManager
import com.lans.foodricion.data.source.network.SafeApiCall
import com.lans.foodricion.data.source.network.api.FoodricionApi
import com.lans.foodricion.data.source.network.dto.request.SignInRequestDto
import com.lans.foodricion.data.source.network.dto.request.SignUpRequestDto
import com.lans.foodricion.data.source.network.dto.response.toDomain
import com.lans.foodricion.domain.model.Token
import com.lans.foodricion.domain.repository.IAuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val api: FoodricionApi,
    private val dataStoreManager: DataStoreManager,
) : IAuthRepository, SafeApiCall {
    override suspend fun isAuthenticated(): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            try {
                dataStoreManager.getAccessToken().collect { token ->
                    emit(Resource.Success(token != ""))
                }
            } catch (ex: Exception) {
                emit(Resource.Error(ex.message.toString()))
            }
        }
    }

    override suspend fun storeSession(
        userId: String,
        accessToken: String,
        refreshToken: String,
        expiredAt: Long
    ) {
        dataStoreManager.storeData(DataStoreManager.USER_ID, userId)
        dataStoreManager.storeData(DataStoreManager.ACCESS_TOKEN, accessToken)
        dataStoreManager.storeData(DataStoreManager.REFRESH_TOKEN, refreshToken)
        dataStoreManager.storeData(DataStoreManager.EXPIRED_AT, expiredAt)
    }

    override suspend fun signin(email: String, password: String): Flow<Resource<Token>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.signin(
                    SignInRequestDto(
                        email = email,
                        password = password
                    )
                )
                if (response.code == 200) {
                    response.data!!.toDomain()
                } else {
                    throw Exception("Username or password was wrong")
                }
            })
        }
    }

    override suspend fun signup(
        email: String,
        fullname: String,
        password: String
    ): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.signup(
                    SignUpRequestDto(
                        email = email,
                        fullname = fullname,
                        password = password
                    )
                ).code == 201
            })
        }
    }
}