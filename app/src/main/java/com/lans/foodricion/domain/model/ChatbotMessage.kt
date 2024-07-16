package com.lans.foodricion.domain.model

import java.util.Date

data class ChatbotMessage(
    val userId: String,
    val message: String,
    val response: String,
    val timestamp: String
)
