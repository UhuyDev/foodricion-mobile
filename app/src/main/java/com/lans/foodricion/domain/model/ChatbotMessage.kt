package com.lans.foodricion.domain.model

import java.time.LocalDateTime

data class ChatbotMessage(
    val userId: String,
    val message: String,
    val response: String,
    val timestamp: LocalDateTime
)
