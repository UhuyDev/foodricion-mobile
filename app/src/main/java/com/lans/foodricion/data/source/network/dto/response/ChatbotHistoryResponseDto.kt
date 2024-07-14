package com.lans.foodricion.data.source.network.dto.response

import android.os.Build
import androidx.annotation.RequiresApi
import com.lans.foodricion.domain.model.ChatbotMessage
import com.squareup.moshi.Json
import java.time.LocalDateTime

data class ChatbotHistoryResponseDto(
    @field:Json(name = "user_id")
    val userId: String,
    val message: String,
    val response: String,
    val timestamp: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun ChatbotHistoryResponseDto.toDomain() = ChatbotMessage(
    userId = userId,
    message = message,
    response = response,
    timestamp = LocalDateTime.now()
)
