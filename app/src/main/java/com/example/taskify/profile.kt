package com.example.taskify

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskify.database.UserRepository
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun profile(
    navController: NavController,
    userRepository: UserRepository,
) {
    val username = userRepository.getUserName()
    val userID = userRepository.getCurrentUserId()
    val exampleTaskId = userRepository.fetchUserTasks(userID).firstOrNull()?.id ?: -1L
    val coroutineScope = rememberCoroutineScope()
    val navNum by remember {
        mutableStateOf(3)
    }
    Scaffold(modifier = Modifier.padding(
        top = 10.dp,
        bottom = 10.dp
    ),
        topBar = {
            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(all = 10.dp))
                {
                    Icon(painter = painterResource(
                        id =R.drawable.userprofile ) ,
                        contentDescription ="" ,
                        tint = Color(0xFF6368D9)
                        , modifier = Modifier
                            .background(
                                shape =
                                RoundedCornerShape(40.dp),
                                color = Color.Transparent)
                            .size(30.dp)
                    )
                    Text(
                        text = username,
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontFamily = FontFamily.Cursive,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp
                        )
                    )}
            })
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
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = "",
                                tint = Color(0xFF6368D9),
                                modifier = Modifier.size(30.dp)
                            )
                        })
                } else {
                    IconButton(
                        onClick = {
                            navController.navigate("calender")
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.calendar),
                                contentDescription = "",
                                tint = Color(0xFFA0A4FF),
                                modifier = Modifier.size(30.dp)
                            )
                        })
                }
                if (navNum == 2) {
                    IconButton(
                        onClick = {
                            if (exampleTaskId != -1L) {
                                navController.navigate("notification/$exampleTaskId")
                            } else {
                                Log.d("home navigation", "else block of calender notification")
                            }
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.notification),
                                contentDescription = "",
                                tint = Color(0xFF6368D9),
                                modifier = Modifier.size(30.dp)
                            )
                        })
                } else {
                    IconButton(
                        onClick = {
                            if (exampleTaskId != -1L) {
                                navController.navigate("notification/$exampleTaskId")
                            } else {
                                Log.d("home navigation", "else block of profile notification")
                            }
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.notification),
                                contentDescription = "",
                                tint = Color(0xFFA0A4FF),
                                modifier = Modifier.size(30.dp)
                            )
                        })}
                if (navNum == 3) {
                    IconButton(
                        onClick = {
                            navController.navigate("profile")
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = "",
                                tint = Color(0xFF6368D9),
                                modifier = Modifier.size(30.dp)
                            )
                        })
                } else {
                    IconButton(
                        onClick = {
                            navController.navigate("profile")
                        },
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.profile),
                                contentDescription = "",
                                tint = Color(0xFFA0A4FF),
                                modifier = Modifier.size(30.dp)
                            )
                        }) }}
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
            Column(
                verticalArrangement = Arrangement.spacedBy(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    elevation = CardDefaults.elevatedCardElevation(
                        10.dp
                    ),
                    shape = RoundedCornerShape(
                        topEnd = 50.dp,
                        bottomStart = 50.dp
                    ),
                    colors = CardDefaults.cardColors(
                        Color(0xFF6368D9)
                    ),
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    content = {
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
                            Image(
                                painter = painterResource(id = R.drawable.splash),
                                contentDescription = "",
                                modifier = Modifier
                                    .weight(2f)
                                    .size(150.dp),
                            )

//
                        ElevatedButton(shape = RoundedCornerShape(
                            topEnd = 20.dp,
                            bottomStart = 20.dp
                        ), colors = ButtonDefaults.buttonColors(
                            Color.White
                        ),
                            elevation = ButtonDefaults.elevatedButtonElevation(
                                defaultElevation = 5.dp
                            ),
                            modifier = Modifier.align(Alignment.CenterHorizontally), onClick = {
                                navController.navigate(Screens.calender)
                            },
                            content = {
                                Text(
                                    text = "Details",
                                    style = TextStyle(
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF6368D9)
                                    )
                                )
                            })
                    })
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                )
                {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .background(
                                Color(0xFF6368D9),
                                shape = RoundedCornerShape(
                                    topEnd = 30.dp,
                                    bottomStart = 30.dp
                                )
                            )
                            .fillMaxWidth()
                            .padding(12.dp)
                            .clickable(onClick = {
                                navController.navigate(Screens.userAccountInfo)
                            })
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "",
                            tint = Color.White
                        )
                        Text(
                            modifier = Modifier,
                            text = "Account Information",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(700),
                                color = Color.White,
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
                                Color(0xFF6368D9),
                                shape = RoundedCornerShape(
                                    topStart = 30.dp,
                                    bottomEnd = 30.dp
                                )
                            )
                            .clickable {
                                coroutineScope.launch {
                                    userRepository.logoutUser()
//                                    userRepository.clearUserData()
                                    navController.navigate(Screens.loginScreen) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = true
                                        }
                                    }
                                }
//                                    navController.popBackStack(
//                                        Screens.signUp,
//                                        inclusive = false
//                                    )
                            }

                            .fillMaxWidth()
                            .padding(12.dp)

                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = R.drawable.login),
                            contentDescription = "",
                            tint = Color.White
                        )
                        Text(
                            text = "Log out",
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight(700),
                                color = Color.White,
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
