package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IAuthRepository
import com.lans.foodricion.domain.usecase.SignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SignUpInteractor @Inject constructor(
    private val authRepository: IAuthRepository
) : SignUpUseCase {
    override suspend fun invoke(email: String, fullname: String, password: String): Flow<Resource<Boolean>> {
        return authRepository.signup(
            email = email,
            fullname = fullname,
            password = password
        ).flowOn(Dispatchers.IO)
    }
}