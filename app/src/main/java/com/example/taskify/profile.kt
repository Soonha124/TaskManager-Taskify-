package com.example.taskify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profile(navController: NavController) {
    var navNum by remember {
        mutableStateOf(3)
    }
    Scaffold(modifier = Modifier.padding(
        top = 10.dp,
        bottom = 10.dp
    ), topBar = {
        Text(
            modifier = Modifier.padding(all = 10.dp),
            text = "Profile",
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF6368D9),
                textAlign = TextAlign.Center,
                letterSpacing = 0.28.sp,
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
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 20.dp)
                .padding(innerPadding)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxWidth())
            {
                Box(modifier = Modifier
                    .height(30.dp)
                )
                {
                    Image(painter = painterResource(
                        id =R.drawable.ic_launcher_background ) ,
                        contentDescription ="" ,
                        colorFilter = ColorFilter.tint(Color.Blue)
                    , modifier = Modifier.background(shape =
                        RoundedCornerShape(40.dp),
                            color = Color.Transparent)
                            .align(Alignment.TopStart)
                    )
                }
                Text(text = "Soonha",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF6368D9),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.28.sp,
                    )
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                Card(elevation = CardDefaults.elevatedCardElevation(
                    10.dp,
                    pressedElevation = 50.dp,
                    hoveredElevation = 30.dp
                ),
                    shape = RoundedCornerShape(30.dp),
                    colors = CardDefaults.cardColors(
                        Color(0xFF6368D9)
                    ),
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    onClick = { /*TODO*/ })

                {
                    Text(
                        modifier = Modifier.padding(15.dp),
                        text = "Amazing",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(700),
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    )
                    Row {
                        Text(
                            modifier = Modifier
                                .padding(15.dp)
                                .weight(3f),
                            text = "You have Completed\n 103 task!",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.28.sp,
                            )
                        )
                        Image(
                            painter = painterResource(id = R.drawable.diary),
                            contentDescription = "",
//                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .weight(1f)
                                .size(100.dp, 100.dp),
                            colorFilter = ColorFilter.tint(Color.LightGray)
                        )
                    }
                    Row(modifier = Modifier.padding(start = 20.dp)) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .background(
                                    Color(0xFFD6D8FD),
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .width(100.dp)
                                .height(46.dp)
                                .fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(15.dp),
                                text = "Details",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF6368D9),
                                    textAlign = TextAlign.Center,
                                    letterSpacing = 0.28.sp,
                                )
                            )
                        }
                    }

                }
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                Color(0xFFD6D8FD),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .fillMaxWidth()
                            .padding(12.dp)
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "",
                            tint = Color(0xFF6368D9)
                        )
                        Text(
                            text = "Account Information",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF6368D9),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.28.sp,
                            )
                        )

                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                Color(0xFFD6D8FD),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .fillMaxWidth()
                            .padding(12.dp)

                    ) {
                        Icon(
                            Icons.Default.Settings,
                            contentDescription = "",
                            tint = Color(0xFF6368D9)
                        )
                        Text(
                            text = "Settings",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF6368D9),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.28.sp,
                            )
                        )

                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                Color(0xFFD6D8FD),
                                shape = RoundedCornerShape(30.dp)
                            )
                            .fillMaxWidth()
                            .padding(12.dp)

                    ) {
                        Icon(
                            Icons.Default.ArrowForward,
                            contentDescription = "",
                            tint = Color(0xFF6368D9)
                        )
                        Text(
                            text = "Log out",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF6368D9),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.28.sp,
                            )
                        )

                    }
                }

            }
        }
    }
}

@Preview
@Composable
fun profilePreview() {
    profile(rememberNavController())
}