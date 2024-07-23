package com.lans.foodricion.presentation.screen.edit_profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.GetMeUseCase
import com.lans.foodricion.domain.usecase.UpdateProfileMetricsUseCase
import com.lans.foodricion.domain.usecase.UpdateProfileUseCase
import com.lans.foodricion.domain.usecase.validator.ValidatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val updateProfileMetricsUseCase: UpdateProfileMetricsUseCase,
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

            is EditProfileUIEvent.AgeChanged -> {
                if (event.age.isEmpty()) {
                    return
                }

                if (!event.age.all { char -> char.isDigit() }) {
                    return
                }

                _state.value = _state.value.copy(
                    age = _state.value.age.copy(
                        value = event.age
                    )
                )
            }

            is EditProfileUIEvent.HeightChanged -> {
                if (event.height.isEmpty()) {
                    return
                }

                if (!event.height.all { char -> char.isDigit() }) {
                    return
                }

                _state.value = _state.value.copy(
                    height = _state.value.height.copy(
                        value = event.height
                    )
                )
            }

            is EditProfileUIEvent.WeightChanged -> {
                if (event.weight.isEmpty()) {
                    return
                }

                if (!event.weight.all { char -> char.isDigit() }) {
                    return
                }

                _state.value = _state.value.copy(
                    weight = _state.value.weight.copy(
                        value = event.weight
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

                        updateProfileMetricsUseCase.invoke(
                            age = stateValue.age.value.toInt(),
                            height = stateValue.height.value.toInt(),
                            weight = stateValue.weight.value.toInt()
                        ).collect { responseMetric ->
                            when (responseMetric) {
                                is Resource.Success -> {
                                    _state.value = _state.value.copy(
                                        isSuccess = responseMetric.data,
                                        isLoading = false
                                    )
                                }

                                is Resource.Error -> {
                                    _state.value = _state.value.copy(
                                        error = "Update profile metrics failed",
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

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = "Update profile failed",
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