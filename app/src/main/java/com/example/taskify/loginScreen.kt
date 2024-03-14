package com.example.taskify

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskify.database.UserRepository
import com.example.taskify.ui.theme.components.ClickableLoginTextComponent
import com.example.taskify.ui.theme.components.MyTextFieldComponent
import com.example.taskify.ui.theme.components.passwordMyTextFieldComponent


@Composable
fun loginScreen(navController: NavController, userRepository: UserRepository){
    var userName by remember {
        mutableStateOf("")
    }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginSuccess by remember { mutableStateOf(false) }
    var (checked, onCheckedChange) = rememberSaveable{
        mutableStateOf(false)
    }
    val context  = LocalContext.current

    val isFieldsEmpty =  email.isNotEmpty() &&
            email.endsWith("@gmail.com")
            && password.isNotEmpty()
            && checked.and(true)

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize())
    {
    Box(modifier = Modifier.height(100.dp))
    {

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
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

    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(1.dp)) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = "Remember me.",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                color = Color(0xFF6368D9),
                textAlign = TextAlign.Center,
                letterSpacing = 0.32.sp,
            )
        )
    }

            UnderLinedTextComponent(value = "Forgot Password ?")

            Button(
                enabled = checkFieldsFilled(userName, email, password),
                colors = ButtonDefaults.buttonColors(Color(0xFF6368D9)),
                modifier = Modifier.width(261.dp),
                onClick = {
                    val loggedIn = userRepository.loginUser(userName,email,password,)
                    if (loggedIn){
                        loginSuccess = true
                        userRepository.saveUserName(userName)
                        Toast.makeText(context,"Welcome Again",
                            Toast.LENGTH_LONG).show()
                    }
                    else{
                        Toast.makeText(context,"try Again", Toast.LENGTH_LONG).show()

                    }

                }) {
                Text(
                    text = "Log in",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(600),
                        color = if(isFieldsEmpty) Color(0xFFFFFFFF) else Color(0xFFEEA3A3),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.32.sp,
                    )
                )
            }
            if (loginSuccess){
                navController.navigate(Screens.homeScreen)
            }
            ClickableLoginTextComponent(tryingToLogin = false,
                onTextSelected = {
                    navController.navigate(Screens.signUp)
                })

        }
    }


}

@Composable
fun UnderLinedTextComponent(value:String){
    Text(text = value,
        modifier = Modifier,
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
        ),
        color = Color(0xFF6368D9),
        textAlign = TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}

@Preview
@Composable
fun previewLoginScreen(){
//    loginScreen(rememberNavController())
}