package com.lans.foodricion.domain.interactor

import com.lans.foodricion.domain.repository.IAuthRepository
import com.lans.foodricion.domain.usecase.SignOutUseCase
import javax.inject.Inject

class SignOutInteractor @Inject constructor(
    private val authRepository: IAuthRepository
) : SignOutUseCase {
    override suspend fun invoke() {
        authRepository.signOut()
    }
}