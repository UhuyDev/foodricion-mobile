package com.lans.foodricion.domain.usecase

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.ChatbotMessage
import kotlinx.coroutines.flow.Flow

interface GetChatbotHistoryUseCase {
    suspend fun invoke(): Flow<Resource<List<ChatbotMessage>>>
}