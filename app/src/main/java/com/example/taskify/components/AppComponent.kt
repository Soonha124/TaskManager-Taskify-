package com.example.taskify.components

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
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
import androidx.compose.ui.window.Dialog
import com.example.taskify.R
import com.example.taskify.database.Task
import com.example.taskify.database.UserRepository
import kotlinx.coroutines.launch

@Composable
fun MyTextFieldComponent(
    labelValue:String, painterResource: Painter,
    onTextSelected: (String) -> Unit,
    modifier: Modifier
){
    val textValue = rememberSaveable {
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

    val password = rememberSaveable {
        mutableStateOf("")
    }
    val passwordVisible = remember {
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
                R.drawable.see
            }
            else
            {
                R.drawable.manhide
            }
            val description = if (passwordVisible.value) {
                "Hide password"
            } else {
                "Show Password"
            }
            IconButton(onClick = {
                passwordVisible.value = !passwordVisible.value })
            {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = iconImage),
                    contentDescription = description,
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

    val confirmPassword = rememberSaveable {
        mutableStateOf("")
    }
    val confirmPasswordVisible = remember {
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
            Image(modifier = Modifier.size(20.dp),
                painter = painterResource,
                contentDescription = "",
                colorFilter = ColorFilter.tint(
                    Color(0xFF6368D9)
                )


            )
        },
        trailingIcon = {
            val iconImage = if (confirmPasswordVisible.value) {
                R.drawable.see
            } else {
                R.drawable.manhide
            }
            val description = if (confirmPasswordVisible.value) {
                "Hide password"
            } else {
                "Show Password"
            }
            IconButton(onClick = {
                confirmPasswordVisible.value = !confirmPasswordVisible.value
            })
            {
                Image(modifier = Modifier.size(30.dp),
                    painter = painterResource(id = iconImage),
                    contentDescription = description,
                )
            }
        },
        visualTransformation =
        if (confirmPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
    )
}
@Composable
fun ButtonComponent(value: String,
                    onButtonClicked:()-> Unit,
                    isEnabled : Boolean = false){
    Button(
        modifier = Modifier.width(261.dp),
        colors = ButtonDefaults.buttonColors(
            if(isEnabled) Color(0xFF6368D9) else Color(0xFFF75675)
        ),
        onClick = {
            onButtonClicked.invoke()
        },
        enabled = isEnabled
    )
    {
        Text(
            text = value,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
                letterSpacing = 0.32.sp,
                color = if(isEnabled) Color.White else Color.Black
            )
        )
    }
}
@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean = true,
                                onTextSelected: (String) -> Unit)
{
    val initialText = if(tryingToLogin) "Already have an account? " else
        "Don't have an account? "
    val loginText = if(tryingToLogin)"Log In" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Color(0xFF484FFD))) {
            pushStringAnnotation(
                tag = loginText,
                annotation = loginText
            )
            append(loginText)
        }
    }

    ClickableText(style = TextStyle(
        fontSize = 15.sp,
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
                    "ClickableTextComponent",
                    "{${span.item}}"
                )
                if (span.item == loginText) {
                    onTextSelected(span.item)
                }
            }
        })
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun categories(
    userRepository: UserRepository,
    category:String
)
{
    var tasks = userRepository.getTasksByCategory(category)

    var showDialog by remember { mutableStateOf(false) }
    var taskToDelete by remember { mutableStateOf<Task?>(null) }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier) {

        if (tasks.isNotEmpty()) {
            LazyColumn {
                items(tasks) { task ->
                    ElevatedCard(shape = RoundedCornerShape(topEnd = 30.dp,
                        bottomStart = 30.dp),
                        colors = CardDefaults.elevatedCardColors(
                            Color(0xFF6368D9)
                        ),
                        modifier = Modifier
                            .combinedClickable(
                                onClick = {
                                    Toast
                                        .makeText(
                                            context,
                                            "Double click to delete",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                },
                                onDoubleClick = {
                                    taskToDelete = task
                                    showDialog = true
                                })
                            .fillMaxWidth()
                            .padding(10.dp),
                        )
                    {
                        Column(horizontalAlignment = Alignment.CenterHorizontally,
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
                                    .align(Alignment.Start)
                            )
                        }

                    }
                }
            }
        } else {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize())
            {
                Image(painter = painterResource(id = R.drawable.no_task),
                    contentDescription = "",
                    Modifier.size(50.dp))
                Text(text = "No tasks found",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF6368D9),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.28.sp,
                    ))

            }
        }

        if (showDialog) {
            DeleteConfirmationDialog(
                onDismiss = { showDialog = false },
                onConfirm = {
                    taskToDelete?.let {
                        coroutineScope.launch {
                            val success = it.id?.let { it1 -> userRepository.deleteTask(it1) }
                            if (success == true) {
                                tasks = userRepository.getTasksByCategory(category)
                            }
                        }
                    }
                    showDialog = false
                }
            )
        }


    }
}

@Composable
fun DeleteConfirmationDialog(onDismiss: () -> Unit, onConfirm: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = Color.White,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Delete Task", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Are you sure you want to delete this task?")
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = onConfirm) {
                        Text("Delete", color = Color.Red)
                    }
                }
            }
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
        shape = RoundedCornerShape(topEnd = 40.dp, bottomStart = 40.dp) ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFF6368D9))
                .fillMaxSize()
                .padding(10.dp)
            )
        {
            Icon(painter = painterResource(id = iconResourceId),
                contentDescription = "",
                tint = Color.White,
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

@Preview
@Composable
fun p(){
    CategoryCard(category = "text here",
        iconResourceId = R.drawable.work) {
        
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
                shape = RoundedCornerShape(
                    topEnd = 30.dp,
                    bottomStart = 30.dp
                ),
                color = if (selected) Color(0xFF6368D9)
                else Color(0xFFD2D4FF)
            )
            .clickable { onTagClicked() }
            .padding(8.dp)
    ) {
        Text(
            text = label,
            color =  if (selected) Color.White
            else Color(0xFF0D0E0F)
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