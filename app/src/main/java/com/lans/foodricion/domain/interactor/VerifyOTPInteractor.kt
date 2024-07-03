package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IUserRepository
import com.lans.foodricion.domain.usecase.VerifyOTPUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class VerifyOTPInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : VerifyOTPUseCase {
    override suspend fun invoke(
        email: String,
        otp: String,
        newPassword: String
    ): Flow<Resource<Boolean>> {
        return userRepository.verifyOTP(email, otp, newPassword).flowOn(Dispatchers.IO)
    }
}