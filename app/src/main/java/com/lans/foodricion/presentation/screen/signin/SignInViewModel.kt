package com.lans.foodricion.presentation.screen.signin

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.SignInUseCase
import com.lans.foodricion.domain.usecase.StoreSessionUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatorUseCase
import com.lans.foodricion.utils.decodeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val storeSessionUseCase: StoreSessionUseCase,
    private val validatorUseCase: ValidatorUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SignInUIState())
    val state: State<SignInUIState> get() = _state

    fun onEvent(event: SignInUIEvent) {
        when (event) {
            is SignInUIEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = _state.value.email.copy(
                        value = event.email
                    )
                )
            }

            is SignInUIEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = _state.value.password.copy(
                        value = event.password
                    )
                )
            }

            SignInUIEvent.SignInButtonClicked -> {
                signin()
            }
        }
    }

    private fun signin() {
        val stateValue = _state.value

        val emailResult = validatorUseCase.email.invoke(stateValue.email.value)
        val passwordResult = validatorUseCase.password.invoke(stateValue.password.value)

        val hasErrors = listOf(
            emailResult,
            passwordResult
        ).any { !it.isSuccess }

        if (hasErrors) {
            _state.value = stateValue.copy(
                email = stateValue.email.copy(
                    error = emailResult.errorMessage
                ),
                password = stateValue.password.copy(
                    error = passwordResult.errorMessage
                )
            )
            return
        }

        viewModelScope.launch {
            signInUseCase.invoke(
                email = stateValue.email.value,
                password = stateValue.password.value
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isLoggedIn = response.data.accessToken != "",
                            isLoading = false
                        )
                        storeSessionUseCase.invoke(
                            decodeToken(response.data.accessToken),
                            response.data
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