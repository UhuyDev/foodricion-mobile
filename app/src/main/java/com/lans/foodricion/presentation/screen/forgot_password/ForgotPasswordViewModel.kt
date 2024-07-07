package com.lans.foodricion.presentation.screen.forgot_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.ForgotPasswordUseCase
import com.lans.foodricion.domain.usecase.VerifyOTPUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val verifyOTPUseCase: VerifyOTPUseCase,
    private val validatorUseCase: ValidatorUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ForgotPasswordUIState())
    val state: State<ForgotPasswordUIState> get() = _state

    fun onEvent(event: ForgotPasswordUIEvent) {
        when (event) {
            is ForgotPasswordUIEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = _state.value.email.copy(
                        value = event.email
                    )
                )
            }

            is ForgotPasswordUIEvent.OTPChanged -> {
                _state.value = _state.value.copy(
                    otp = _state.value.otp.copy(
                        value = event.otp
                    )
                )
            }

            is ForgotPasswordUIEvent.NewPasswordChanged -> {
                _state.value = _state.value.copy(
                    newPassword = _state.value.newPassword.copy(
                        value = event.newPassword
                    )
                )
            }

            is ForgotPasswordUIEvent.SendCodeButtonClicked -> {
                sendCode()
            }

            is ForgotPasswordUIEvent.SubmitButtonClicked -> {
                submit()
            }
        }
    }

    private fun sendCode() {
        val stateValue = _state.value

        if (stateValue.email.value.isBlank() || stateValue.isCounting) {
            return
        }

        viewModelScope.launch {
            while (_state.value.remainingTime >= 0) {
                delay(1000L)
                _state.value = _state.value.copy(remainingTime = _state.value.remainingTime - 1)
            }
            _state.value = _state.value.copy(isCounting = false)
        }

        val emailResult = validatorUseCase.email.invoke(stateValue.email.value)

        if (!emailResult.isSuccess) {
            _state.value = stateValue.copy(
                email = stateValue.email.copy(
                    error = emailResult.errorMessage
                )
            )
            return
        }

        viewModelScope.launch {
            forgotPasswordUseCase.invoke(
                email = stateValue.email.value
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isOTPSent = response.data,
                            isCounting = true,
                            remainingTime = 60,
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

    private fun submit() {
        val stateValue = _state.value

        val otpResult = validatorUseCase.otp.invoke(stateValue.otp.value)
        val newPasswordResult = validatorUseCase.password.invoke(stateValue.newPassword.value)

        val hasErrors = listOf(
            otpResult,
            newPasswordResult
        ).any { !it.isSuccess }

        if (hasErrors) {
            _state.value = stateValue.copy(
                otp = stateValue.otp.copy(
                    error = otpResult.errorMessage
                ),
                newPassword = stateValue.newPassword.copy(
                    error = newPasswordResult.errorMessage
                )
            )
            return
        }

        viewModelScope.launch {
            verifyOTPUseCase.invoke(
                email = stateValue.email.value,
                otp = stateValue.otp.value,
                newPassword = stateValue.newPassword.value
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            isSuccess = response.data,
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