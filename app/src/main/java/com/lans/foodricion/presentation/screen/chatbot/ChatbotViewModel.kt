package com.lans.foodricion.presentation.screen.chatbot

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.InputWrapper
import com.lans.foodricion.domain.usecase.GetChatbotHistoryUseCase
import com.lans.foodricion.domain.usecase.SendChatbotMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ChatbotViewModel @Inject constructor(
    private val sendChatbotMessageUseCase: SendChatbotMessageUseCase,
    private val getChatbotHistoryUseCase: GetChatbotHistoryUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ChatbotUIState())
    val state: State<ChatbotUIState> get() = _state

    fun onEvent(event: ChatbotUIEvent) {
        when (event) {
            is ChatbotUIEvent.MessageChanged -> {
                _state.value = _state.value.copy(
                    message = _state.value.message.copy(
                        value = event.message
                    )
                )
            }

            is ChatbotUIEvent.SendButtonClicked -> {
                sendMessage()
            }
        }
    }

    private fun sendMessage() {
        val stateValue = state.value

        if (stateValue.message.value.isBlank()) {
            return
        }

        viewModelScope.launch {
            sendChatbotMessageUseCase.invoke(
                message = stateValue.message.value
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isMessageSent = true,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = response.message,
                            isMessageSent = false,
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isMessageSent = false,
                            isLoading = true
                        )
                    }

                    else -> Unit
                }
            }
        }
    }

    fun getMessages() {
        viewModelScope.launch {
            getChatbotHistoryUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault())
                        val sortedMessages = response.data.sortedBy { dateFormat.parse(it.timestamp) }
                        _state.value = _state.value.copy(
                            messages = sortedMessages,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = response.message,
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }

                    else -> Unit
                }
            }
        }
    }
}