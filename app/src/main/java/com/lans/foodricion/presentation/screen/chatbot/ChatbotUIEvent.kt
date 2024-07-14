package com.lans.foodricion.presentation.screen.chatbot

sealed class ChatbotUIEvent {
    data class MessageChanged(val message: String) : ChatbotUIEvent()
    object SendButtonClicked : ChatbotUIEvent()
}