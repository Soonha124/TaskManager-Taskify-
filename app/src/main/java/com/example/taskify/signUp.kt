package com.example.taskify

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taskify.database.UserRepository
import com.example.taskify.components.ButtonComponent
import com.example.taskify.components.ClickableLoginTextComponent
import com.example.taskify.components.MyTextFieldComponent
import com.example.taskify.components.confirmPasswordMyTextFieldComponent
import com.example.taskify.components.passwordMyTextFieldComponent

@SuppressLint("SuspiciousIndentation")
@Composable
fun signUp(
    navController: NavHostController,
    userRepository: UserRepository,

) {
    val context = LocalContext.current
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var registrationSuccess by rememberSaveable { mutableStateOf(false) }
    var attemptRegistration by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(attemptRegistration) {
        if (attemptRegistration && password == confirmPassword && checkFieldsFilled(username, email, password)) {
            registrationSuccess = userRepository.registerUser(username, email, password)
            if (registrationSuccess) {
                val userId = userRepository.getCurrentUserId()  // Ensure this gets the correct user ID
                if (userId != -1L) {
                    navController.navigate(Screens.homeScreen)
                } else {
                    Toast.makeText(context, "Registration error. Please try again.",
                        Toast.LENGTH_LONG).show()
                }
            }
            attemptRegistration = false
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    )
    {

        Text(
            text = "Create Your\n Account",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight(800),
                color = Color(0xFF6368D9),
                textAlign = TextAlign.Center,
                letterSpacing = 0.48.sp,
            )
        )
        Spacer(modifier = Modifier.height(30.dp))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        )
        {
           MyTextFieldComponent(
               labelValue = "UserName",
               painterResource = painterResource(id = R.drawable.userprofile),
               onTextSelected = {
                                username = it
                   checkFieldsFilled(username,email, password)
               },
           )

           MyTextFieldComponent(
               labelValue = "Email",
               painterResource = painterResource(id = R.drawable.emailinbox),
               onTextSelected = {
                email = it
                   checkFieldsFilled(username,email, password)

               },
           )
           passwordMyTextFieldComponent(
               labelValue = "Password",
               painterResource = painterResource(id = R.drawable.password) ,
               onTextSelected = {
                   password = it
                   checkFieldsFilled(username,email, password)

               },
           )
           confirmPasswordMyTextFieldComponent(
               labelValue = "Confirm Password",
               painterResource = painterResource(id = R.drawable.password),
               onTextSelected = {
                                confirmPassword = it
                   checkFieldsFilled(username,email, password)

               },
           )
        }
        Spacer(modifier = Modifier.height(30.dp))
        ButtonComponent(
            value = "Register",
            onButtonClicked = {
                              attemptRegistration = true

            },
            isEnabled = checkFieldsFilled(username, email, password)

        )
        Spacer(modifier = Modifier.height(10.dp))
        ClickableLoginTextComponent(tryingToLogin = true,
            onTextSelected = {
                navController.navigate(Screens.loginScreen)
            })
        if (registrationSuccess){
            navController.navigate(Screens.homeScreen)
        }

    }

}


fun checkFieldsFilled(username: String, email: String, password: String): Boolean {
    return username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
            && email.endsWith("@gmail.com") && email.length>=11
}

@Preview
@Composable
fun previewSignUp() {
//    signUp(rememberNavController(),
//        userRepository )
}
