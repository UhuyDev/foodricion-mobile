package com.lans.foodricion.presentation.screen.change_password

sealed class ChangePasswordUIEvent {
    data class OldPasswordChanged(val oldPassword: String) : ChangePasswordUIEvent()
    data class NewPasswordChanged(val newPassword: String) : ChangePasswordUIEvent()
    object SubmitButtonClicked : ChangePasswordUIEvent()
}
