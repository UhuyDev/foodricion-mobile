package com.lans.foodricion.presentation.screen.signup

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.SignUpUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val validatorUseCase: ValidatorUseCase
) : ViewModel() {

    private val _state = mutableStateOf(SignUpUIState())
    val state: State<SignUpUIState> get() = _state

    fun onEvent(event: SignUpUIEvent) {
        when (event) {
            is SignUpUIEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = _state.value.email.copy(
                        value = event.email
                    )
                )
            }

            is SignUpUIEvent.FullnameChanged -> {
                _state.value = _state.value.copy(
                    fullname = _state.value.email.copy(
                        value = event.fullname
                    )
                )
            }

            is SignUpUIEvent.PasswordChanged -> {
                _state.value = _state.value.copy(
                    password = _state.value.password.copy(
                        value = event.password
                    )
                )
            }

            is SignUpUIEvent.ConfirmPasswordChanged -> {
                _state.value = _state.value.copy(
                    confirmPassword = _state.value.email.copy(
                        value = event.confirmPassword
                    )
                )
            }

            is SignUpUIEvent.SignUpButtonClicked -> {
                signup()
            }
        }
    }

    private fun signup() {
        val stateValue = _state.value

        val emailResult = validatorUseCase.email.invoke(stateValue.email.value)
        val fullnameResult = validatorUseCase.fullname.invoke(stateValue.fullname.value)
        val passwordResult = validatorUseCase.password.invoke(stateValue.password.value)
        val confirmPasswordResult = validatorUseCase.confirmPassword.invoke(
            stateValue.password.value,
            stateValue.confirmPassword.value
        )

        val hasErrors = listOf(
            emailResult,
            fullnameResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.isSuccess }

        if (hasErrors) {
            _state.value = stateValue.copy(
                email = stateValue.email.copy(
                    error = emailResult.errorMessage
                ),
                fullname = stateValue.fullname.copy(
                    error = fullnameResult.errorMessage
                ),
                password = stateValue.password.copy(
                    error = passwordResult.errorMessage
                ),
                confirmPassword = stateValue.confirmPassword.copy(
                    error = confirmPasswordResult.errorMessage
                )
            )
            return
        }

        viewModelScope.launch {
            signUpUseCase.invoke(
                email = stateValue.email.value,
                fullname = stateValue.fullname.value,
                password = stateValue.password.value
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            signUpResponse = response.data,
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