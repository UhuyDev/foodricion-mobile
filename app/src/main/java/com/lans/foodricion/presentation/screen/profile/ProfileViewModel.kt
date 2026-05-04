package com.lans.foodricion.presentation.screen.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.GetMeUseCase
import com.lans.foodricion.domain.usecase.SignOutUseCase
import com.lans.foodricion.presentation.screen.chatbot.ChatbotUIEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getMeUseCase: GetMeUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ProfileUIState())
    val state: State<ProfileUIState> get() = _state

    fun onEvent(event: ProfileUIEvent) {
        when (event) {
            is ProfileUIEvent.LogoutButtonClicked -> {
                signOut()
            }
        }
    }

    fun getMe() {
        viewModelScope.launch {
            getMeUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            user = response.data,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            user = null,
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

    private fun signOut() {
        viewModelScope.launch {
            signOutUseCase.invoke()
        }
    }
}