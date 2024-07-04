package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Token
import com.lans.foodricion.domain.repository.IAuthRepository
import com.lans.foodricion.domain.usecase.SignInUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignInInteractor @Inject constructor(
    private val authRepository: IAuthRepository
) : SignInUseCase {
    override suspend fun invoke(email: String, password: String): Flow<Resource<Token>> {
        return authRepository.signin(
            email = email,
            password = password
        ).flowOn(Dispatchers.IO)
    }
}