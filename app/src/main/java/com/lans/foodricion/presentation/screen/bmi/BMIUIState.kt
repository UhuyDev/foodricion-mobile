package com.lans.foodricion.presentation.screen.bmi

import com.lans.foodricion.domain.model.InputWrapper

data class BMIUIState(
    val height: InputWrapper = InputWrapper("0"),
    val weight: InputWrapper = InputWrapper("0"),
    val score: String = "",
    val category: String = "",
    val message: String = ""
)
