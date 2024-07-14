package com.lans.foodricion.presentation.screen.chatbot

import com.lans.foodricion.domain.model.ChatbotMessage
import com.lans.foodricion.domain.model.InputWrapper

data class ChatbotUIState(
    val message: InputWrapper = InputWrapper(),
    val isLoading: Boolean = false,
    var error: String = "",
    val isMessageSent: Boolean = false,
    val messages: List<ChatbotMessage> = emptyList()
)
