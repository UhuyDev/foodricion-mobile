package com.lans.foodricion.presentation.screen.forgot_password

sealed class ForgotPasswordUIEvent {
    data class EmailChanged(val email: String) : ForgotPasswordUIEvent()
    data class OTPChanged(val otp: String) : ForgotPasswordUIEvent()
    data class NewPasswordChanged(val newPassword: String) : ForgotPasswordUIEvent()
    object SendCodeButtonClicked : ForgotPasswordUIEvent()
    object SubmitButtonClicked : ForgotPasswordUIEvent()
}
