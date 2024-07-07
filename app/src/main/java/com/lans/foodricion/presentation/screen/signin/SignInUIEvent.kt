package com.lans.foodricion.presentation.screen.signin

sealed class SignInUIEvent {
    data class EmailChanged(val email: String): SignInUIEvent()
    data class PasswordChanged(val password: String): SignInUIEvent()
    object SignInButtonClicked: SignInUIEvent()
}