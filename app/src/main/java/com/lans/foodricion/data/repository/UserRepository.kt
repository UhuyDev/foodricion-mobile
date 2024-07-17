package com.lans.foodricion.data.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.data.source.network.SafeApiCall
import com.lans.foodricion.data.source.network.api.FoodricionApi
import com.lans.foodricion.data.source.network.dto.request.ForgotPasswordRequestDto
import com.lans.foodricion.data.source.network.dto.request.VerifyOTPRequestDto
import com.lans.foodricion.data.source.network.dto.response.toDomain
import com.lans.foodricion.domain.model.User
import com.lans.foodricion.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: FoodricionApi
) : IUserRepository, SafeApiCall {
    override suspend fun me(): Flow<Resource<User>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.me()
                response.toDomain()
            })
        }
    }

    override suspend fun forgotPassword(email: String): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.forgotPassword(
                    ForgotPasswordRequestDto(
                        email = email
                    )
                ).code == 200
            })
        }
    }

    override suspend fun verifyOTP(
        email: String,
        otp: String,
        newPassword: String
    ): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.verifyOTP(
                    VerifyOTPRequestDto(
                        email = email,
                        otp = otp,
                        newPassword = newPassword
                    )
                ).code == 200
            })
        }
    }
}