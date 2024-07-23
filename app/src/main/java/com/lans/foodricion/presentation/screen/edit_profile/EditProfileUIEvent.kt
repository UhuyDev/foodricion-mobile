package com.lans.foodricion.presentation.screen.edit_profile

sealed class EditProfileUIEvent {
    data class FullnameChanged(val fullname: String) : EditProfileUIEvent()
    data class EmailChanged(val email: String) : EditProfileUIEvent()
    data class AgeChanged(val age: String) : EditProfileUIEvent()
    data class HeightChanged(val height: String) : EditProfileUIEvent()
    data class WeightChanged(val weight: String) : EditProfileUIEvent()
    object SubmitButtonClicked : EditProfileUIEvent()
    object DeleteButtonClicked : EditProfileUIEvent()
}
