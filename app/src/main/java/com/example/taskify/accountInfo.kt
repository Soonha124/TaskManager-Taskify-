package com.example.taskify

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.taskify.database.UserRepository

@SuppressLint("SuspiciousIndentation")
@Composable
fun userAccountInfo(
    navController: NavController,
    userRepository: UserRepository,
) {
    val userEmail = userRepository.getUserEmail() ?: "Email not Available"

    val userPassword = userRepository.getUserPassword()
    Scaffold()

    { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            verticalArrangement = Arrangement.Center.also { Arrangement.spacedBy(30.dp) },
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Card(
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 10.dp
                ),
                shape = RoundedCornerShape(
                    topEnd = 30.dp,
                    bottomStart = 30.dp
                ),
                colors = CardDefaults.cardColors(
                    Color(0xFF6368D9)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center.also
                        {
                            Arrangement.spacedBy(10.dp)
                        },
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                    {
                        userRepository.saveUserEmail(userEmail)
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            Text(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .weight(1f),
                                text = "Your Email:",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(700),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    letterSpacing = 0.28.sp
                                )
                            )

                            Text(
                                text = userEmail,
                                modifier = Modifier
                                    .padding(15.dp)
                                    .weight(2f),
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(700),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    letterSpacing = 0.28.sp
                                )
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        )
                        {
                            Text(
                                modifier = Modifier
                                    .padding(15.dp)
                                    .weight(1f),
                                text = "Your Password :",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(700),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    letterSpacing = 0.28.sp
                                )
                            )

                            Text(
                                text = "$userPassword",
                                modifier = Modifier
                                    .padding(15.dp)
                                    .weight(2f),
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(700),
                                    textAlign = TextAlign.Center,
                                    color = Color.White,
                                    letterSpacing = 0.28.sp
                                )
                            )
                        }
                    }
                },
            )
            Spacer(modifier = Modifier.height(30.dp))
            Card(modifier = Modifier.clickable
                (onClick = {
                navController.navigate(Screens.profile)
            }),
                elevation = CardDefaults.elevatedCardElevation(
                    defaultElevation = 10.dp
                ), shape = RoundedCornerShape(
                    topEnd = 20.dp,
                    bottomStart = 20.dp
                ),
                colors = CardDefaults.cardColors(
                    Color(0xFFF89400)
                ),
                content = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "backArrow",
                        tint = Color.White,
                        modifier = Modifier.padding(15.dp)
                    )
                })


        }


    }
}

@Preview
@Composable
fun preview() {
//    userAccountInfo(navController = rememberNavController())
}