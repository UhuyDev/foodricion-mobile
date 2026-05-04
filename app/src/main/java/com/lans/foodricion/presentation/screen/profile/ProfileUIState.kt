package com.lans.foodricion.presentation.screen.profile

import com.lans.foodricion.domain.model.User

data class ProfileUIState(
    val isLoading: Boolean = false,
    var error: String = "",
    var user: User? = null
)
