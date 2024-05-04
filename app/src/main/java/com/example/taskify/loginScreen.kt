package com.example.taskify

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskify.database.UserRepository
import com.example.taskify.components.ClickableLoginTextComponent
import com.example.taskify.components.MyTextFieldComponent
import com.example.taskify.components.passwordMyTextFieldComponent


@Composable
fun loginScreen(
    navController: NavController,
    userRepository: UserRepository
)
{
    var userName by remember {mutableStateOf("")}
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginSuccess by remember { mutableStateOf(false) }
    val context  = LocalContext.current

    val isFieldsEmpty =  email.isNotEmpty() &&
            email.endsWith("@gmail.com")
            && password.isNotEmpty()

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize())
    {
    Box(modifier = Modifier.height(100.dp))
    {

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                text = "Welcome\nback!",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontWeight = FontWeight(800),
                    color = Color(0xFF6368D9),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.48.sp,
                ))
        }
    }
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp))
        {

            MyTextFieldComponent(
                labelValue = "UserName",
                painterResource = painterResource(id = R.drawable.userprofile),
                onTextSelected = {
                    userName = it
                })
            MyTextFieldComponent(
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
            
            Spacer(modifier = Modifier.height(30.dp))

            Button(
                enabled = checkFieldsFilled(userName, email, password),
                colors = ButtonDefaults.buttonColors(Color(0xFF6368D9)),
                modifier = Modifier.width(261.dp),
                onClick = {
                    val loginSuccessful = userRepository.loginUser(userName, email, password)
                    if (loginSuccessful) {
                        val userId = userRepository.getCurrentUserId()
                        userRepository.saveUserId(userId)
                        userRepository.saveUserEmail(email)
                        if (userId != -1L) {
                            userRepository.saveUserName(userName)
                            userRepository.saveUserId(userId)
                            Toast.makeText(context, "Welcome Again", Toast.LENGTH_LONG).show()
                            loginSuccess = true
                        } else {
                            Toast.makeText(context, "Login failed. Please retry.",
                                Toast.LENGTH_LONG).show()
                        }
                    } else {
                        Toast.makeText(context, "Check your credentials",
                            Toast.LENGTH_LONG).show()
                    }

                }) {
                Text(
                    text = "Log in",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = if(isFieldsEmpty) Color(0xFFFFFFFF) else Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.32.sp
                    ))
            }
            if (loginSuccess)
            {
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


}


@Preview
@Composable
fun previewLoginScreen(){
//    loginScreen(rememberNavController())
}