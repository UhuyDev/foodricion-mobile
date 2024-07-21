package com.lans.foodricion.presentation.screen.edit_profile

import com.lans.foodricion.domain.model.InputWrapper

data class EditProfileUIState(
    val fullname: InputWrapper = InputWrapper(),
    val email: InputWrapper = InputWrapper(),
    val isLoading: Boolean = false,
    var error: String = "",
    var isSuccess: Boolean = false
)
