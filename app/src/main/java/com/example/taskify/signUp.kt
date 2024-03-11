package com.example.taskify

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taskify.ui.theme.components.ClickableLoginTextComponent
import com.example.taskify.ui.theme.data.LoginViewModel
import com.example.taskify.ui.theme.data.UIEvents

@Composable
fun signUp(
    navController: NavHostController,
    loginViewModel: LoginViewModel = viewModel(),
) {
    var (username, setUsername) = rememberSaveable {
        mutableStateOf("")
    }
    var (email, setEmail) = rememberSaveable {
        mutableStateOf("")
    }
    var (password, onPasswordChange) = rememberSaveable {
        mutableStateOf("")
    }
    var (confirmPassword, onconfirmPasswordChange) = rememberSaveable {
        mutableStateOf("")
    }

    var isPasswordSame by remember {
        mutableStateOf(false)
    }
    var passwordVisible = remember {
        mutableStateOf(false)
    }
    val localFocusManager = LocalFocusManager.current
    val isFieldsNotEmpty = username.isNotEmpty() && email.isNotEmpty()
            && email.endsWith("@gmail.com")
            && password.isNotEmpty() && confirmPassword.isNotEmpty()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    )
    {
        AnimatedVisibility(isPasswordSame) {
            Text(
                text = "Password is not Matching",
                color = Color(0xFF6368D9)
            )
        }

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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        )
        {
            OutlinedTextField(modifier = Modifier
                .background(
                    color = Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                ), value = username,
                label = { Text(text = "UserName") },
                onValueChange = {
                    setUsername(it)
                    loginViewModel.onEvent(UIEvents.UserNameChange(it))
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                leadingIcon = {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = "",
                        tint = Color(0xFF6368D9)
                    )
                }
            )
            OutlinedTextField(
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            color = Color(0xFF6368D9)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ),
                value = email,
                placeholder = {
                    Text(text = "Email")
                },
                onValueChange = {
                    setEmail(it)
                    loginViewModel.onEvent(UIEvents.EmailChange(it))
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                leadingIcon = {
                    Icon(
                        Icons.Default.MailOutline,
                        contentDescription = "",
                        tint = Color(0xFF6368D9)
                    )
                },
            )
            OutlinedTextField(
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            color = Color(0xFF6368D9)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ),
                value = password,
                placeholder = {
                    Text(text = "Password")
                },
                onValueChange = {
                    onPasswordChange(it)
                    loginViewModel.onEvent(UIEvents.PasswordChange(it))
                },

                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "",
                        tint = Color(0xFF6368D9)
                    )
                },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                trailingIcon = {
                    val iconImage = if (passwordVisible.value) {
                        R.drawable.open_eyes
                    } else {
                        R.drawable.close_eye
                    }
                    var description = if (passwordVisible.value) {
                        "Hide password"
                    } else {
                        "Show Password"
                    }
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(
                            painter = painterResource(id = iconImage),
                            contentDescription = description,
                            tint = Color(0xFF6368D9)
                        )
                    }
                },
                visualTransformation =
                if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            )

            OutlinedTextField(
                modifier = Modifier
                    .background(
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            color = Color(0xFF6368D9)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ),
                value = confirmPassword,
                placeholder = {
                    Text(text = "Confirm Password")
                },
                onValueChange = {
                    onconfirmPasswordChange(it)
                    loginViewModel.onEvent(UIEvents.ConfirmPasswordChange(it))
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions {
                    localFocusManager.clearFocus()
                },
                singleLine = true,
                maxLines = 1,
                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = "",
                        tint = Color(0xFF6368D9)
                    )
                },
                trailingIcon = {
                    val iconImage = if (passwordVisible.value) {
                        R.drawable.open_eyes
                    } else {
                        R.drawable.close_eye
                    }
                    var description = if (passwordVisible.value) {
                        "Hide password"
                    } else {
                        "Show Password"
                    }
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(
                            painter = painterResource(id = iconImage),
                            contentDescription = description,
                            tint = Color(0xFF6368D9)
                        )
                    }
                },
                visualTransformation =
                if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(enabled = isFieldsNotEmpty,
            colors = if (isFieldsNotEmpty) ButtonDefaults.buttonColors(
                Color(0xFF6368D9)
            ) else ButtonDefaults.buttonColors(
                Color(0xFFEEA3A3)
            ),
            modifier = Modifier.width(261.dp),
            onClick = {
                loginViewModel.onEvent(UIEvents.RegisterButtonClicked)

                isPasswordSame = password != confirmPassword
                if (!isPasswordSame) {
                    navController.navigate(Screens.homeScreen)
                }
            }
        )
        {
            Text(
                text = "Register",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(600),
                    color = if (isFieldsNotEmpty == false) Color(0xFFFFA7A7)
                    else Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.32.sp,
                )
            )
        }
        ClickableLoginTextComponent(tryingToLogin = true,
            onTextSelected = {
                navController.navigate(Screens.loginScreen)
            })
    }
}


@Preview
@Composable
fun previewSignUp() {
    signUp(rememberNavController())
}
