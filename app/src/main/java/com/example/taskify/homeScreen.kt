package com.example.taskify

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.taskify.ui.theme.components.CategoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(navController: NavController) {
    var navNum by remember {
        mutableStateOf(0)
    }
    Scaffold(
        modifier = Modifier.padding(
            top = 10.dp,
            bottom = 10.dp
        ),
        topBar = {
            TopAppBar(title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.padding(all = 10.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .shadow(
                                elevation = 10.dp,
                                shape = RoundedCornerShape(20.dp)
                            )
                    ) {
                        Image(
                            painter = painterResource(
                                id = R.drawable.ic_launcher_background
                            ),
                            contentDescription = "",
                            colorFilter = ColorFilter.tint(Color.Blue)
                        )
                    }
                    Text(
                        text = "Soonha",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    )
                }
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                elevation = FloatingActionButtonDefaults.elevation(
                    10.dp
                ),
                containerColor = Color(0xFF673AB7),
                contentColor = Color.White,
                onClick = {
                    navController.navigate("createTask")
                }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add task"
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,

        bottomBar = {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            )
            {
                IconButton(
                    onClick = {
                        navController.navigate("homeScreen")
                    }) {
                    Icon(
                        painterResource(id = R.drawable.home),
                        contentDescription = "",
                        tint = if (navNum == 0) Color(0xFF6368D9) else Color(0xFFA0A4FF),
                        modifier = Modifier.size(30.dp)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate(Screens.calender)
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.calendar),
                        contentDescription = "",
                        tint = if (navNum == 1) Color(0xFF6368D9) else Color(0xFFA0A4FF),
                        modifier = Modifier.size(30.dp)
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate("notification")
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "",
                        tint = if (navNum == 2) Color(0xFF6368D9) else Color(0xFFA0A4FF),

                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(
                    onClick = {
                        navController.navigate("profile")
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "",
                        tint = if (navNum == 3) Color(0xFF6368D9) else Color(0xFFA0A4FF),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

        }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 20.dp)
                .padding(innerPadding)
        ) {
            Text(
                text = "Let’s check out your today’s task",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF6368D9),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.24.sp,
                )
            )
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    Color(0xFF6368D9)
                ),
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .padding(
                            top = 30.dp,
                            start = 20.dp
                        )
                        .height(5.dp)
                        .shadow(
                            elevation = 5.dp,
                            shape = RoundedCornerShape(2.dp)
                        ),
                    progress = 0.45f,
                    color = Color(0xFFEEEFFF),
                    trackColor = Color(0xFFCDCFEE)
                )
            }
            Text(
                text = "Task categories",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF6368D9),
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.24.sp,
                )
            )
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CategoryCard("Work ", R.drawable.work) {
                            navController.navigate(Screens.workCategory)
                        }
                        CategoryCard("Study ", R.drawable.university) {
                            navController.navigate(Screens.studyCategory)
                        }
                        CategoryCard("Other ", R.drawable.folder) {
                            navController.navigate(Screens.otherCategory)
                        }
}}} } }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun previewHomeScreen() {
    homeScreen(rememberNavController())
}