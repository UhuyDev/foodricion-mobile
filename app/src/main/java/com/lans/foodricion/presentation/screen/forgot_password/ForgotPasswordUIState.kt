package com.lans.foodricion.presentation.screen.forgot_password

import com.lans.foodricion.domain.model.InputWrapper

data class ForgotPasswordUIState(
    val email: InputWrapper = InputWrapper(),
    val otp: InputWrapper = InputWrapper(),
    val newPassword: InputWrapper = InputWrapper(),
    val isLoading: Boolean = false,
    var error: String = "",
    var isOTPSent: Boolean = false,
    val isSuccess: Boolean = false,
    var isSendCodeClicked: Boolean = false,
    var remainingTime: Int = 60
)
