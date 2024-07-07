package com.lans.foodricion.domain.usecase

import com.lans.foodricion.domain.model.Token

interface StoreSessionUseCase {
    suspend fun invoke(userId: String, token: Token)
}