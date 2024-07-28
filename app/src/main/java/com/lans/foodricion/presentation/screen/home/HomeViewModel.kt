package com.lans.foodricion.presentation.screen.home

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lans.foodricion.data.Resource
import com.lans.foodricion.domain.model.Classification
import com.lans.foodricion.domain.tensorflow.FoodClassifier
import com.lans.foodricion.domain.usecase.DeleteDailyNutritionUseCase
import com.lans.foodricion.domain.usecase.GetDailyNutritionsUseCase
import com.lans.foodricion.domain.usecase.GetImageTempUriUseCase
import com.lans.foodricion.domain.usecase.GetMeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMeUseCase: GetMeUseCase,
    private val getImageTempUriUseCase: GetImageTempUriUseCase,
    private val getDailyNutritionsUseCase: GetDailyNutritionsUseCase,
    private val deleteDailyNutritionUseCase: DeleteDailyNutritionUseCase,
    private val foodClassifier: FoodClassifier
) : ViewModel() {

    private val _state = mutableStateOf(HomeUIState())
    val state: State<HomeUIState> get() = _state

    fun getTempUri(authority: String) {
        _state.value = _state.value.copy(
            tempUri = getImageTempUriUseCase.invoke(authority = authority)
        )
    }

    fun classify(bitmap: Bitmap, rotation: Int): List<Classification> {
        Log.d("CLASSIFIER", foodClassifier.classify(bitmap, rotation).toString())
        return foodClassifier.classify(bitmap, rotation)
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

    fun getDailyNutritions() {
        viewModelScope.launch {
            getDailyNutritionsUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            nutritionHistory = response.data,
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            nutritionHistory = emptyList(),
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

    fun deleteDailyNutrition(dailyNutritionId: Int) {
        viewModelScope.launch {
            deleteDailyNutritionUseCase.invoke(dailyNutritionId = dailyNutritionId)
                .collect { response ->
                    when (response) {
                        is Resource.Success -> {
                            _state.value = _state.value.copy(
                                isHistoryDeleted = true,
                                isLoading = false
                            )
                        }

                        is Resource.Error -> {
                            _state.value = _state.value.copy(
                                isHistoryDeleted = false,
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