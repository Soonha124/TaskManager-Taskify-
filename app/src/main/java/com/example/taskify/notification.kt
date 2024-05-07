package com.example.taskify

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import com.example.taskify.database.Task
import com.example.taskify.database.UserRepository

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun notification(
    navController: NavController,
    userRepository: UserRepository,
    taskId: Long)
{

    val taskList = remember { mutableStateOf(listOf<Task>()) }
    val selectedTask = remember(taskId) { taskList.value.find { it.id == taskId } }

    val userId = userRepository.getCurrentUserId()
    val task by remember(taskId) { mutableStateOf(userRepository.getTaskById(taskId)) }
    val exampleTaskId = userRepository.fetchUserTasks(userId).firstOrNull()?.id ?: -1L

    LaunchedEffect(key1 = taskId) {
        taskList.value =
            userRepository.fetchUserTasks(userId)
    }
    val navNum by remember {
        mutableStateOf(2)
    }


    Scaffold(modifier = Modifier.padding(
        top = 10.dp,
        bottom = 10.dp
    ),
        topBar = {
            Text(
                modifier = Modifier.padding(all = 10.dp),
                text = "Notifications",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF6368D9),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.28.sp
                )
            )

        },
        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            )
            {
                if (navNum == 0) {
                    IconButton(
                        onClick = {
                            navController.navigate("homeScreen")
                        }) {
                        Icon(
                            painterResource(id = R.drawable.home),
                            contentDescription = "",
                            tint = Color(0xFF6368D9),
                            modifier = Modifier.size(30.dp)
                        )

                    }
                } else {
                    IconButton(
                        onClick = {
                            navController.navigate("homeScreen")
                        }) {
                        Icon(
                            painterResource(id = R.drawable.home),
                            contentDescription = "",
                            tint = Color(0xFFA0A4FF),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                if (navNum == 1) {
                    IconButton(
                        onClick = {
                            navController.navigate("calender")
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "",
                            tint = Color(0xFF6368D9),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            navController.navigate("calender")
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar),
                            contentDescription = "",
                            tint = Color(0xFFA0A4FF),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                if (navNum == 2) {
                    IconButton(enabled = true,
                        onClick = {
                            if (exampleTaskId != -1L) {
                                navController.navigate("notification/$exampleTaskId")
                            } else {
                                Log.d("home navigation", "else block of home notification")
                            }
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification),
                            contentDescription = "",
                            tint = Color(0xFF6368D9),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                } else {
                    IconButton(enabled = false,
                        onClick = {
                            if (exampleTaskId != -1L) {
                                navController.navigate("notification/$exampleTaskId")
                            } else {
                                Log.d("notification navigation", "else block of notification to notification")
                            }
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification),
                            contentDescription = "",
                            tint = Color(0xFFA0A4FF),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
                if (navNum == 3) {
                    IconButton(
                        onClick = {
                            navController.navigate("profile")
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "",
                            tint = Color(0xFF6368D9),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            navController.navigate("profile")
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = "",
                            tint = Color(0xFFA0A4FF),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

            }

        })
    {innerPadding->
        Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(innerPadding))
        {
            if (task != null)
        {
            LazyColumn(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                    items(taskList.value) { task ->
                        NotificationItem(
                            task = task,
                            isSelected = task.id == selectedTask?.id
                        )
                    }
            }
        }
            else
            {
                Column(verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Nothing to Show")
                }

            }

        }
    }

}

@Composable
fun NotificationItem(task: Task, isSelected: Boolean) {
    ElevatedCard(
        shape = RoundedCornerShape(topEnd = 30.dp,
            bottomStart = 30.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(if (isSelected)
                Color(0xFF6368D9) else Color(0xFF43463f))

    ) {
        Column(modifier = Modifier.padding(16.dp))
        {
            Text(text = task.title,
                style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = task.description)
            Text(text = task.date)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun notificationPreview() {
}