package com.lans.foodricion.domain.interactor

import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.repository.IChatbotRepository
import com.lans.foodricion.domain.usecase.SendChatbotMessageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SendChatBotMessageInteractor @Inject constructor(
    private val chatBotRepository: IChatbotRepository
): SendChatbotMessageUseCase {
    override suspend fun invoke(message: String): Flow<Resource<Boolean>> {
        return chatBotRepository.sendMessage(message).flowOn(Dispatchers.IO)
    }
}