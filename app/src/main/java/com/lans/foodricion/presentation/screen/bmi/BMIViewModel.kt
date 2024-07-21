package com.lans.foodricion.presentation.screen.bmi

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.domain.usecase.CalculateBMIUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BMIViewModel @Inject constructor(
    private val calculateBMIUseCase: CalculateBMIUseCase
) : ViewModel() {

    private val _state = mutableStateOf(BMIUIState())
    val state: State<BMIUIState> get() = _state

    fun onEvent(event: BMIUIEvent) {
        when (event) {
            is BMIUIEvent.HeightChanged -> {
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

            is BMIUIEvent.WeightChanged -> {
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

            is BMIUIEvent.CalculateButtonClicked -> {
                calculate()
            }
        }
    }

    private fun calculate() {
        viewModelScope.launch {
            val stateValue = _state.value

            val result = calculateBMIUseCase.invoke(
                height = stateValue.height.value.toDouble(),
                weight = stateValue.weight.value.toDouble()
            )

            _state.value = _state.value.copy(
                score = result.score,
                category = result.category,
                message = result.message
            )
        }
    }
}