package com.lans.foodricion.presentation.screen.home

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.lans.foodricion.domain.model.Classification
import com.lans.foodricion.domain.tensorflow.FoodClassifier
import com.lans.foodricion.domain.usecase.GetImageTempUriUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getImageTempUriUseCase: GetImageTempUriUseCase,
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
}