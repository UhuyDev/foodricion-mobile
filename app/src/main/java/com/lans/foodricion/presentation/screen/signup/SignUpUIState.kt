package com.lans.foodricion.presentation.screen.signup

import com.lans.foodricion.domain.model.InputWrapper

data class SignUpUIState(
    val email: InputWrapper = InputWrapper(),
    val fullname: InputWrapper = InputWrapper(),
    val password: InputWrapper = InputWrapper(),
    val confirmPassword: InputWrapper = InputWrapper(),
    val isLoading: Boolean = false,
    var error: String = "",
    val signUpResponse: Boolean = false
)
