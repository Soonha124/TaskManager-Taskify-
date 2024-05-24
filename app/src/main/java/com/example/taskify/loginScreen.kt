package com.example.taskify

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskify.components.ButtonComponent
import com.example.taskify.database.UserRepository
import com.example.taskify.components.ClickableLoginTextComponent
import com.example.taskify.components.MyTextFieldComponent
import com.example.taskify.components.passwordMyTextFieldComponent


@SuppressLint("SuspiciousIndentation")
@Composable
fun loginScreen(
    navController: NavController,
    userRepository: UserRepository,
) {
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginSuccess by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val isFieldsEmpty = email.isNotEmpty() &&
            email.endsWith("@gmail.com")
            && password.isNotEmpty()


        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        )
        {

            Image(painter = painterResource(id = R.drawable.signupimage),
                contentDescription = "cartoon",
                modifier = Modifier.size(200.dp)
            )

                    Text(
                        text = "Welcome\nback!",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(800),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.48.sp,
                        )
                    )

            Spacer(modifier = Modifier.height(10.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(5.dp))
            
            {
                MyTextFieldComponent(
                    modifier = Modifier
                        .border(
                            border = BorderStroke(5.dp, Color.White),
                            shape = RoundedCornerShape(
                                topEnd = 20.dp,
                                bottomStart = 20.dp
                            )
                        ),
                    labelValue = "UserName",
                    painterResource = painterResource(id = R.drawable.userprofile),
                    onTextSelected = {
                        userName = it
                    })
                MyTextFieldComponent(
                    modifier = Modifier,
                    labelValue = "Email",
                    painterResource = painterResource(id = R.drawable.emailinbox),
                    onTextSelected = {
                        email = it
                    })
                passwordMyTextFieldComponent(
                    labelValue = "Password",
                    painterResource = painterResource(id = R.drawable.password),
                    onTextSelected = {
                        password = it
                    })

                Spacer(modifier = Modifier.height(10.dp))

                ButtonComponent(
                    value = "Log In",
                    isEnabled = checkFieldsFilled(userName, email, password),
                    onButtonClicked = {
                        val loginSuccessful = userRepository.loginUser(userName, email, password)
                        if (loginSuccessful) {
                            val userId = userRepository.getCurrentUserId()
                            userRepository.saveUserId(userId)
                            userRepository.saveUserEmail(email)
                            if (userId != -1L) {
                                userRepository.saveUserName(userName)
                                userRepository.saveUserId(userId)
                                Toast.makeText(context, "Welcome Again", Toast.LENGTH_SHORT).show()
                                loginSuccess = true
                            } else {
                                Toast.makeText(
                                    context, "Login failed. Please retry.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        } else {
                            Toast.makeText(
                                context, "Invalid Data",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    })
                if (loginSuccess) {
                    navController.navigate(Screens.homeScreen)
                }
                ClickableLoginTextComponent(
                    tryingToLogin = false,
                    onTextSelected =
                    {
                        navController.navigate(Screens.signUp)
                    }
                )

            }

        }
//    }

}


@Preview
@Composable
fun previewLoginScreen() {
//    loginScreen(rememberNavController())
}