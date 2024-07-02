package com.lans.foodricion.presentation.screen.signup

import com.lans.foodricion.presentation.screen.signin.SignInUIEvent

sealed class SignUpUIEvent {
    data class EmailChanged(val email: String): SignUpUIEvent()
    data class FullnameChanged(val fullname: String): SignUpUIEvent()
    data class PasswordChanged(val password: String): SignUpUIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String): SignUpUIEvent()
    object SignUpButtonClicked: SignUpUIEvent()
}
