package com.lans.foodricion.data.repository

import com.lans.foodricion.data.Resource
import com.lans.foodricion.data.source.network.SafeApiCall
import com.lans.foodricion.data.source.network.api.FoodricionApi
import com.lans.foodricion.data.source.network.dto.request.ChatbotRequestDto
import com.lans.foodricion.data.source.network.dto.response.toDomain
import com.lans.foodricion.domain.model.ChatbotMessage
import com.lans.foodricion.domain.repository.IChatbotRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChatbotRepository @Inject constructor(
    private val api: FoodricionApi
) : IChatbotRepository, SafeApiCall {
    override suspend fun sendMessage(message: String): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                api.chatbot(
                    ChatbotRequestDto(
                        message = message
                    )
                ).code == 200
            })
        }
    }

    override suspend fun getMessages(): Flow<Resource<List<ChatbotMessage>>> {
        return flow {
            emit(Resource.Loading)
            emit(safeCall {
                val response = api.chatbotHistory()
                if (response.code == 200) {
                    response.data?.map { history ->
                        history.toDomain()
                    } ?: emptyList()
                } else {
                    throw Exception("Can't get chatbot messages")
                }
            })
        }
    }
}