package com.example.taskify.ViewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskify.Screens

class NavigationViewModel(context: Context) : ViewModel() {
    private val preferences = context.getSharedPreferences("NavigationPreferences", Context.MODE_PRIVATE)

     private val isLoggedIn = MutableLiveData<Boolean>()

    init {
        isLoggedIn.value = checkIsLoggedIn()
    }
    var lastScreen: String?
        get() = if (isLoggedIn.value == true) preferences.getString("LAST_SCREEN", Screens.homeScreen) else Screens.splashScreen
        set(value) {
            if (isLoggedIn.value == true  && value != lastScreen) {
                preferences.edit().putString("LAST_SCREEN", value).apply()
            }
        }
    private fun checkIsLoggedIn(): Boolean {
        return preferences.getBoolean("isLoggedIn", false)
    }
//    fun login() {
//        preferences.edit().putBoolean("isLoggedIn", true).apply()
//        isLoggedIn.value = true
//    }
//    fun logout() {
//        preferences.edit().putBoolean("isLoggedIn", false).apply()
//        isLoggedIn.value = false
//    }
//
//
//    fun resetForDevelopment() {
//        preferences.edit().clear().apply()
//        isLoggedIn.value = false
//    }
}

class NavigationViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NavigationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NavigationViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
