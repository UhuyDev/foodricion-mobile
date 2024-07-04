package com.lans.foodricion.presentation.screen.signin

import com.lans.foodricion.domain.model.InputWrapper

data class SignInUIState(
    val email: InputWrapper = InputWrapper(),
    val password: InputWrapper = InputWrapper(),
    val isLoading: Boolean = false,
    var error: String = "",
    val isLoggedIn: Boolean = false
)
