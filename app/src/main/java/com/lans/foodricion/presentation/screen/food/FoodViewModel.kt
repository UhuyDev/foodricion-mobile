package com.lans.foodricion.presentation.screen.food

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.usecase.AddDailyNutritionUseCase
import com.lans.foodricion.domain.usecase.GetFoodsUseCase
import com.lans.foodricion.domain.usecase.SearchFoodByNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val getFoodsUseCase: GetFoodsUseCase,
    private val addDailyNutritionUseCase: AddDailyNutritionUseCase,
    private val searchFoodByNameUseCase: SearchFoodByNameUseCase
) : ViewModel() {

    private val _state = mutableStateOf(FoodUIState())
    val state: State<FoodUIState> get() = _state

    fun onEvent(event: FoodUIEvent) {
        when (event) {
            is FoodUIEvent.SearchChanged -> {
                _state.value = _state.value.copy(
                    search = _state.value.search.copy(
                        value = event.search
                    )
                )

                if (_state.value.foods.isEmpty()) {
                    return
                }

                if (_state.value.search.value.isNotBlank()) {
                    searchFood()
                } else {
                    getFoods()
                }
            }
        }
    }

    private fun searchFood() {
        viewModelScope.launch {
            searchFoodByNameUseCase.invoke(
                foodList = _state.value.foodsDefault,
                foodName = state.value.search.value
            ).collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            foods = response.data,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            foods = emptyList(),
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

    fun getFoods() {
        viewModelScope.launch {
            getFoodsUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            foods = response.data,
                            foodsDefault = response.data,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            foods = emptyList(),
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