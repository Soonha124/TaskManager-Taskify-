package com.example.taskify.ui.theme.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskify.R
import com.example.taskify.database.UserRepository

@Composable
fun MyTextFieldComponent(
    labelValue:String, painterResource: Painter,
    onTextSelected: (String) -> Unit,
){
    var textValue = rememberSaveable {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            ),
        value = textValue.value,
        label = { Text(text = labelValue) },
        onValueChange = {
            textValue.value = it
            onTextSelected(it)
//            loginViewModel.onEvent(UIEvents.UserNameChange(it))
        },
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        leadingIcon = {
            Icon(modifier = Modifier.size(20.dp),
                painter = painterResource,
                contentDescription = "",
                tint = Color(0xFF6368D9)
            )
        }
    )
}
@SuppressLint("SuspiciousIndentation")
@Composable
fun passwordMyTextFieldComponent(
    labelValue:String, painterResource: Painter,
    onTextSelected: (String) -> Unit
){
    val localFocusManager = LocalFocusManager.current
    
    var password = rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            ),
        value = password.value,
        label = {
            Text(text = labelValue)
        },
        onValueChange = {
        password.value = it
            onTextSelected(it)
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Next
        ),
        keyboardActions = KeyboardActions {
            localFocusManager.moveFocus(FocusDirection.Down)
        },
        singleLine = true,
        maxLines = 1,
        leadingIcon = {
            Icon(modifier = Modifier.size(20.dp),
                painter = painterResource,
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
                Icon(modifier = Modifier.size(20.dp),
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
@Composable
fun confirmPasswordMyTextFieldComponent(
    labelValue:String, painterResource: Painter,
    onTextSelected: (String) -> Unit,
){
    val localFocusManager = LocalFocusManager.current

    var confirmPassword = rememberSaveable {
        mutableStateOf("")
    }
    var confirmPasswordVisible = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(10.dp)
            ),
        value = confirmPassword.value,
        label = {
            Text(text = labelValue)
        },
        onValueChange = {
            confirmPassword.value = it
            onTextSelected(it)
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
            Icon(modifier = Modifier.size(20.dp),
                painter = painterResource,
                contentDescription = "",
                tint = Color(0xFF6368D9)
            )
        },
        trailingIcon = {
            val iconImage = if (confirmPasswordVisible.value) {
                R.drawable.open_eyes
            } else {
                R.drawable.close_eye
            }
            var description = if (confirmPasswordVisible.value) {
                "Hide password"
            } else {
                "Show Password"
            }
            IconButton(onClick = { confirmPasswordVisible.value = !confirmPasswordVisible.value }) {
                Icon(modifier = Modifier.size(20.dp),
                    painter = painterResource(id = iconImage),
                    contentDescription = description,
                    tint = Color(0xFF6368D9)
                )
            }
        },
        visualTransformation =
        if (confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
    )
}
@Composable
fun ButtonComponent(value: String, onButtonClicked:()-> Unit, isEnabled : Boolean = false){
    Button(
        modifier = Modifier.width(261.dp),
        colors = ButtonDefaults.buttonColors(
            Color(0xFF6368D9)
        ),
        onClick = {
        onButtonClicked.invoke()
        },
        enabled = isEnabled
    )
    {
        Text(
            text = "Register",
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.32.sp,
                color = Color.White
            )
        )
    }
}
@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean = true,
                                onTextSelected: (String) -> Unit) {
    val initialText = if(tryingToLogin) "Already have an account? " else "Dont have an account? "
    val loginText = if(tryingToLogin)"Log In" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Color(0xFF6368D9))) {
            pushStringAnnotation(
                tag = loginText,
                annotation = loginText
            )
            append(loginText)
        }
    }

    ClickableText(style = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFF9C98A1),
        textAlign = TextAlign.Center,
        letterSpacing = 0.22.sp,
    ), text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                offset, offset
            ).firstOrNull()?.also { span ->
                Log.d(
                    "ClickabletextComponent",
                    "{${span.item}}"
                )
                if (span.item == loginText) {
                    onTextSelected(span.item)
                }
            }
        })
}
@Composable
fun categories(userRepository: UserRepository,
               category: String)
{
    val tasks = userRepository.getTasksByCategory(category)

    Column(modifier = Modifier) {
        if (tasks.isNotEmpty()) {
            LazyColumn {
                items(tasks) { task ->
                    // Display each task here
                    ElevatedCard(shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.elevatedCardColors(
                            Color(0xFF6368D9)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        onClick = { /*TODO*/ })
                    {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(5.dp,
                                Alignment.CenterVertically ),
                            modifier = Modifier
                                .padding(10.dp)
                        ) {
                            Text(
                                text = task.title,
                                color = Color.White,
                                modifier = Modifier
                                    .align(Alignment.Start)
                            )
                            Text(
                                text = task.date,
                                color = Color.White,
                                modifier = Modifier
                                    .align(Alignment.CenterHorizontally)
                            )
                        }

                    }
                }
            }
        } else {
            Text(text = "No tasks found")
        }
    }
}

@Composable
fun CategoryCard(
    category: String, iconResourceId: Int,
    onClick: ()-> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp) ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFF6368D9))
                .fillMaxSize()
                .padding(10.dp)
            ,
        ) {
            Icon(painter = painterResource(id = iconResourceId),
                contentDescription = "",
                tint = Color(0xFFCDCFEE),
                modifier = Modifier.padding(5.dp))
            Text(modifier = Modifier.padding(5.dp),
                text = category,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center )
        }
    }
}

@Composable
fun Tags(
    label: String,
    modifier: Modifier,
    selected: Boolean,
    onTagClicked: () -> Unit
) {

    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                shape = RoundedCornerShape(10.dp),
                color = if (selected) Color(0xFF6368D9)
                else Color(0xFFCFD3FF)
            )
            .clickable { onTagClicked() }
            .padding(8.dp)
    ) {
        Text(
            text = label,
            color =  if (selected) Color.White
            else Color(0xFF0015FD)
        )
        if (selected) {
            Icon(
                Icons.Filled.Check,
                contentDescription = "select",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            )
        }
    }
}
@Composable
fun ExitConfirmationDialog(
    onConfirmExit: () -> Unit,
    onDismiss: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        AlertDialog(
            onDismissRequest = onDismiss,
            text = { Text("Do you want to exit from the app?") },
            confirmButton = {
                Button(onClick = onConfirmExit) {
                    Text("Yes")
                }
            },
            dismissButton = {
                Button(onClick = onDismiss,
                ) {
                    Text("No")
                }
            }
        )
    }

}
@Preview
@Composable
fun e(){
    Tags(label = "work", modifier = Modifier, selected =true ) {
        
    }
//    ExitConfirmationDialog(onConfirmExit = { /*TODO*/ },{})
}