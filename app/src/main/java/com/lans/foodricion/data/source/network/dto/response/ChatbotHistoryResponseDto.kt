package com.lans.foodricion.data.source.network.dto.response

import android.annotation.SuppressLint
import com.lans.foodricion.domain.model.ChatbotMessage
import com.squareup.moshi.Json
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ChatbotHistoryResponseDto(
    @field:Json(name = "user_id")
    val userId: String,
    val message: String,
    val response: String,
    val timestamp: String
)

fun ChatbotHistoryResponseDto.toDomain() = ChatbotMessage(
    userId = userId,
    message = message,
    response = response,
    timestamp = convertTimestamp(timestamp)
)

@SuppressLint("SimpleDateFormat")
fun convertTimestamp(timestamp: String): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
    return SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(parser.parse(timestamp)!!)
}