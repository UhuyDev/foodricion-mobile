package com.lans.foodricion.presentation.screen.change_password

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.ChangePasswordUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val validatorUseCase: ValidatorUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ChangePasswordUIState())
    val state: State<ChangePasswordUIState> get() = _state

    fun onEvent(event: ChangePasswordUIEvent) {
        when (event) {
            is ChangePasswordUIEvent.OldPasswordChanged -> {
                _state.value = _state.value.copy(
                    oldPassword = _state.value.oldPassword.copy(
                        value = event.oldPassword
                    )
                )
            }

            is ChangePasswordUIEvent.NewPasswordChanged -> {
                _state.value = _state.value.copy(
                    newPassword = _state.value.newPassword.copy(
                        value = event.newPassword
                    )
                )
            }

            is ChangePasswordUIEvent.SubmitButtonClicked -> {
                submit()
            }
        }
    }

    private fun submit() {
        val stateValue = _state.value

        val oldPasswordResult = validatorUseCase.password.invoke(stateValue.oldPassword.value)
        val newPasswordResult = validatorUseCase.password.invoke(stateValue.newPassword.value)

        val hasErrors = listOf(
            oldPasswordResult,
            newPasswordResult
        ).any { !it.isSuccess }

        if (hasErrors) {
            _state.value = stateValue.copy(
                oldPassword = stateValue.oldPassword.copy(
                    error = oldPasswordResult.errorMessage
                ),
                newPassword = stateValue.newPassword.copy(
                    error = newPasswordResult.errorMessage
                )
            )
            return
        }

        viewModelScope.launch {
            changePasswordUseCase.invoke(
                oldPassword = stateValue.oldPassword.value,
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