package com.lans.foodricion.presentation.screen.edit_profile

import com.lans.foodricion.domain.model.InputWrapper

data class EditProfileUIState(
    val fullname: InputWrapper = InputWrapper(),
    val email: InputWrapper = InputWrapper(),
    val age: InputWrapper = InputWrapper("0"),
    val height: InputWrapper = InputWrapper("0"),
    val weight: InputWrapper = InputWrapper("0"),
    val isLoading: Boolean = false,
    var error: String = "",
    var isSuccess: Boolean = false
)
