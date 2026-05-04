package com.lans.foodricion.presentation.screen.home

import android.net.Uri
import com.lans.foodricion.domain.model.Classification
import com.lans.foodricion.domain.model.DailyNutrition
import com.lans.foodricion.domain.model.Food
import com.lans.foodricion.domain.model.User

data class HomeUIState(
    var tempUri: Uri = Uri.EMPTY,
    var rotation: Int = 0,
    val isLoading: Boolean = false,
    var error: String = "",
    var classifierResult: List<Classification> = emptyList(),
    val nutritionHistory: List<DailyNutrition> = emptyList(),
    val foodRecommendation: List<Food> = emptyList(),
    var user: User? = null,
    var isDailyNutritionAdded: Boolean = false,
    var isHistoryDeleted: Boolean = false
)
