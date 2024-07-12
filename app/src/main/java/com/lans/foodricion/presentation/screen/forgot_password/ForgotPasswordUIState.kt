package com.lans.foodricion.presentation.screen.forgot_password

import com.lans.foodricion.domain.model.InputWrapper

data class ForgotPasswordUIState(
    val email: InputWrapper = InputWrapper(),
    val otp: InputWrapper = InputWrapper(),
    val newPassword: InputWrapper = InputWrapper(),
    val isOTPLoading: Boolean = false,
    val isVerifyLoading: Boolean = false,
    var error: String = "",
    var isOTPSent: Boolean = false,
    var isCounting: Boolean = false,
    var isSuccess: Boolean = false,
    var remainingTime: Int = 60
)
