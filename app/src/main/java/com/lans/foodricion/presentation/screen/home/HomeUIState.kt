package com.lans.foodricion.presentation.screen.home

import android.net.Uri

data class HomeUIState(
    val tempUri: Uri? = null,
    val isLoading: Boolean = false,
    var error: String = ""
)
