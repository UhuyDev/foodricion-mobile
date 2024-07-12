package com.lans.foodricion.presentation.screen.home

import android.net.Uri
import com.lans.foodricion.domain.model.Classification

data class HomeUIState(
    var tempUri: Uri = Uri.EMPTY,
    var rotation: Int = 0,
    val isLoading: Boolean = false,
    var error: String = "",
    var classifierResult: List<Classification> = emptyList()
)
