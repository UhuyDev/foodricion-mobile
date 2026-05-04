package com.lans.foodricion.presentation.screen.food_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.AddDailyNutritionUseCase
import com.lans.foodricion.domain.usecase.GetFoodByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val getFoodByNameUseCase: GetFoodByNameUseCase,
    private val addDailyNutritionUseCase: AddDailyNutritionUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FoodDetailUIState())
    val state: State<FoodDetailUIState> get() = _state

    fun onEvent(event: FoodDetailUIEvent) {
        when (event) {
            is FoodDetailUIEvent.AddToDailyNutrition -> {
                addDailyNutrition(_state.value.food!!.foodName)
            }
        }
    }

    fun getFood(foodName: String) {
        viewModelScope.launch {
            getFoodByNameUseCase.invoke(
                foodName = foodName
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            food = response.data,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            food = null,
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

    fun addDailyNutrition(foodName: String) {
        viewModelScope.launch {
            addDailyNutritionUseCase.invoke(foodName = foodName)
                .collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                isDailyNutritionAdded = true,
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                isDailyNutritionAdded = false,
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