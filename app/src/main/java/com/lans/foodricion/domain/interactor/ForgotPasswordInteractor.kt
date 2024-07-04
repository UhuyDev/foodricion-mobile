package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IUserRepository
import com.lans.foodricion.domain.usecase.ForgotPasswordUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ForgotPasswordInteractor @Inject constructor(
    private val userRepository: IUserRepository
) : ForgotPasswordUseCase {
    override suspend fun invoke(email: String): Flow<Resource<Boolean>> {
        return userRepository.forgotPassword(email).flowOn(Dispatchers.IO)
    }
}