package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import kotlinx.coroutines.flow.Flow

interface SendChatbotMessageUseCase {
    suspend fun invoke(message: String): Flow<Resource<Boolean>>
}