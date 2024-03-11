package com.example.taskify.ui.theme.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    var registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event : UIEvents){
        when(event){
            is UIEvents.UserNameChange ->{
                registrationUIState.value = registrationUIState.value.copy(
                    userName = event.userName
                )
                printState()
            }
            is UIEvents.EmailChange ->{
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()

            }
            is UIEvents.PasswordChange ->{
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()

            }
            is UIEvents.ConfirmPasswordChange ->{
                registrationUIState.value = registrationUIState.value.copy(
                    confirmPassword = event.confirmPassword
                )
                printState()
            }
            is UIEvents.RegisterButtonClicked -> {
                SignUP()
            }

        }
    }

    private fun SignUP() {
        Log.d(TAG,"Inside_SignUp")
        printState()

    }

    private fun printState(){
        Log.d(TAG,"Inside_Print_State")
        Log.d(TAG,registrationUIState.value.toString())

    }
}