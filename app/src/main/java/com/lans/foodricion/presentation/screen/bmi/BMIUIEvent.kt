package com.lans.foodricion.presentation.screen.bmi

sealed class BMIUIEvent {
    data class HeightChanged(val height: String) : BMIUIEvent()
    data class WeightChanged(val weight: String) : BMIUIEvent()
    object CalculateButtonClicked : BMIUIEvent()
}