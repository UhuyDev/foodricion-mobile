package com.lans.foodricion.presentation.screen.change_password

import com.lans.foodricion.domain.model.InputWrapper

data class ChangePasswordUIState(
    val oldPassword: InputWrapper = InputWrapper(),
    val newPassword: InputWrapper = InputWrapper(),
    val isLoading: Boolean = false,
    var error: String = "",
    var isSuccess: Boolean = false
)
