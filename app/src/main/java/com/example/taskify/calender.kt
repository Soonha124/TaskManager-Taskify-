package com.example.taskify

import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

data class Taskk(
    val category: String,
    val description: String,
    val time: Int,
)

val taskList = listOf(
    Taskk("College Stuff", "Prepare presentaion",  2 ),
    Taskk("University Stuff", "Prepare presentaion", 3),
    Taskk("Work", "Prepare presentaion", 3),
    Taskk("Gym", "Prepare presentaion", 3),
    Taskk("Personal Projects", "Prepare presentaion", 3),
    Taskk("University Stuff", "Prepare presentaion", 3),
    Taskk("University Stuff", "Prepare presentaion", 3),
    Taskk("University Stuff", "Prepare presentaion", 3),
    Taskk("University Stuff", "Prepare presentaion", 3),
    Taskk("University Stuff", "Prepare presentaion", 3),
    Taskk("University Stuff", "Prepare presentaion", 3),
    Taskk("University Stuff", "Prepare presentaion", 3),
    Taskk("University Stuff", "Prepare presentaion", 3),

    )

@Composable
fun calender(navController: NavController) {
    var navNum by remember {
        mutableStateOf(1)
    }
    Scaffold(modifier = Modifier.padding(
        top = 10.dp,
        bottom = 10.dp
    ),
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
                    IconButton(enabled = true,
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
                    IconButton(enabled = false,
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
                    IconButton(
                        onClick = {
                            navController.navigate("notification")
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.notification),
                            contentDescription = "",
                            tint = Color(0xFF6368D9),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                } else {
                    IconButton(
                        onClick = {
                            navController.navigate("notification")
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

        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(paddingValues)
                .padding(all = 20.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 20.dp, start = 10.dp),
                text = "Today’s task",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF6368D9),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.28.sp,
                )
            )
            LazyRow(modifier = Modifier.padding(5.dp)) {
                items(20) {
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .width(45.dp)
                            .height(53.dp)
                            .background(
                                color = Color(0xFFD1D0F9),
                                shape = RoundedCornerShape(size = 10.dp)
                            )
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .padding(5.dp)
                                .fillMaxSize()
                        ) {
                            Text(text = "Apr")
                            Text(text = "7")
                        }
                    }
                }
            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            )
            {
                items(taskList) { task ->
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(286.dp)
                            .height(77.dp)
                            .background(
                                color = Color(0xFFD1D0F9),
                                shape = RoundedCornerShape(size = 15.dp)
                            )
                    ) {
                        Column {
                            Text(text = task.category)
                            Text(text = task.description)
                            Text(text = "Time: ${task.time} hours")

                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))

                }
            }


        }
    }
}

@Preview
@Composable
fun previewCalender() {
    calender(rememberNavController())
}