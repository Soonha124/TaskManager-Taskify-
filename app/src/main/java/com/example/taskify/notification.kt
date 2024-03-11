package com.example.taskify

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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

@Composable
fun notification(navController: NavController) {
    var navNum by remember {
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
                    IconButton(enabled = false,
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
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(all = 20.dp)
        )
        {
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Today",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF6368D9),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.28.sp
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .background(
                        color = Color(0xFF6368D9),
                        shape = RoundedCornerShape(size = 15.dp)
                    )
            )
            {
                Text(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        start = 10.dp, bottom = 20.dp
                    ),
                    text = "5 mint ago",
                    style = TextStyle(
                        color = Color.White
                    )
                )
                Text(
                    modifier = Modifier.padding(
                        top = 25.dp,
                        start = 10.dp,
                    ),
                    text = "Projetc Proposal has been submitted",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Yesterday",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF6368D9),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.28.sp
                )
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
                    .background(
                        color = Color(0xFF6368D9),
                        shape = RoundedCornerShape(size = 15.dp)
                    )
            )
            {
                Text(
                    modifier = Modifier.padding(
                        top = 10.dp,
                        start = 10.dp, bottom = 20.dp
                    ),
                    text = "36 hours ago",
                    style = TextStyle(
                        color = Color.White
                    )
                )
                Text(
                    modifier = Modifier.padding(
                        top = 25.dp,
                        start = 10.dp,
                    ),
                    text = "Project Proposal has been submitted",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun notificationPreview() {
    notification(rememberNavController())
}