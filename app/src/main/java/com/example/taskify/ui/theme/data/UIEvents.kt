package com.example.taskify.ui.theme.data

sealed class UIEvents {
    data class UserNameChange(val userName :String) :UIEvents()
    data class EmailChange(val email :String) :UIEvents()
    data class PasswordChange(val password :String) :UIEvents()
    data class ConfirmPasswordChange(val confirmPassword :String) :UIEvents()

    object RegisterButtonClicked : UIEvents()
}