package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.ChatbotMessage
import com.lans.foodricion.domain.repository.IChatbotRepository
import com.lans.foodricion.domain.usecase.GetChatbotHistoryUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GetChatbotHistoryInteractor @Inject constructor(
    private val chatbotRepository: IChatbotRepository
): GetChatbotHistoryUseCase{
    override suspend fun invoke(): Flow<Resource<List<ChatbotMessage>>> {
        return chatbotRepository.getMessages().flowOn(Dispatchers.IO)
    }
}