package com.example.taskify

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.taskify.ui.theme.components.ButtonComponent
import com.example.taskify.ui.theme.components.ClickableLoginTextComponent
import com.example.taskify.ui.theme.components.MyTextFieldComponent
import com.example.taskify.ui.theme.components.passwordMyTextFieldComponent

@SuppressLint("SuspiciousIndentation")
@Composable
fun signUp(
    navController: NavHostController,
    userRepository: UserRepository,
) {
    var username by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }
    var registrationSuccess by rememberSaveable { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    )
    {
//        AnimatedVisibility(isPasswordSame) {
//            Text(
//                text = "Password is not Matching",
//                color = Color(0xFF6368D9)
//            )
//        }

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
           passwordMyTextFieldComponent(
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
                              if (password == confirmPassword){
                                  val registered = userRepository.registerUser(username, email, password )
                                  if (registered)
                                  {
                                      registrationSuccess = true
                                      userRepository.saveUserName(username)
                                  } else {

                                  }
                              }
                              else {

                              }
            },
            isEnabled = checkFieldsFilled(username, email, password)

        )

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
}

@Preview
@Composable
fun previewSignUp() {
//    signUp(rememberNavController(),
//        userRepository )
}
