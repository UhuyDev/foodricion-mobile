package com.lans.foodricion.domain.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.ChatbotMessage
import kotlinx.coroutines.flow.Flow

interface IChatbotRepository {
    suspend fun sendMessage(message: String): Flow<Resource<Boolean>>
    suspend fun getMessages(): Flow<Resource<List<ChatbotMessage>>>
}