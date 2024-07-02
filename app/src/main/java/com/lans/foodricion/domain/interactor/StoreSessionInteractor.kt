package com.lans.foodricion.domain.interactor

import com.lans.foodricion.domain.model.Token
import com.lans.foodricion.domain.repository.IAuthRepository
import com.lans.foodricion.domain.usecase.StoreSessionUseCase
import javax.inject.Inject

class StoreSessionInteractor @Inject constructor(
    private val authRepository: IAuthRepository
): StoreSessionUseCase {
    override suspend fun invoke(userId: String, token: Token) {
        authRepository.storeSession(
            userId = userId,
            accessToken = token.accessToken,
            refreshToken = token.refreshToken,
            expiredAt = token.expiredAt
        )
    }
}