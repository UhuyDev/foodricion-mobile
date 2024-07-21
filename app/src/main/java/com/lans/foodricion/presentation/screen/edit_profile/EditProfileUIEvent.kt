package com.lans.foodricion.presentation.screen.edit_profile

sealed class EditProfileUIEvent {
    data class FullnameChanged(val fullname: String) : EditProfileUIEvent()
    data class EmailChanged(val email: String) : EditProfileUIEvent()
    object SubmitButtonClicked : EditProfileUIEvent()
    object DeleteButtonClicked : EditProfileUIEvent()
}
