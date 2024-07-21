package com.lans.foodricion.presentation.screen.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.GetMeUseCase
import com.lans.foodricion.domain.usecase.UpdateProfileUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val getMeUseCase: GetMeUseCase,
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val validatorUseCase: ValidatorUseCase
) : ViewModel() {
    private val _state = mutableStateOf(EditProfileUIState())
    val state: State<EditProfileUIState> get() = _state

    fun onEvent(event: EditProfileUIEvent) {
        when (event) {
            is EditProfileUIEvent.FullnameChanged -> {
                _state.value = _state.value.copy(
                    fullname = _state.value.fullname.copy(
                        value = event.fullname
                    )
                )
            }

            is EditProfileUIEvent.EmailChanged -> {
                _state.value = _state.value.copy(
                    email = _state.value.email.copy(
                        value = event.email
                    )
                )
            }

            is EditProfileUIEvent.SubmitButtonClicked -> {
                submit()
            }

            is EditProfileUIEvent.DeleteButtonClicked -> {

            }
        }
    }

    private fun submit() {
        val stateValue = _state.value

        val fullnameResult = validatorUseCase.fullname.invoke(stateValue.fullname.value)
        val emailResult = validatorUseCase.email.invoke(stateValue.email.value)

        val hasErrors = listOf(
            fullnameResult,
            emailResult
        ).any { !it.isSuccess }

        if (hasErrors) {
            _state.value = stateValue.copy(
                fullname = stateValue.fullname.copy(
                    error = fullnameResult.errorMessage
                ),
                email = stateValue.email.copy(
                    error = emailResult.errorMessage
                )
            )
            return
        }

        viewModelScope.launch {
            updateProfileUseCase.invoke(
                fullname = stateValue.fullname.value,
                email = stateValue.email.value
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