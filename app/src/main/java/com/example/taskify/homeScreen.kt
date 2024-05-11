package com.example.taskify

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskify.database.UserRepository
import com.example.taskify.components.CategoryCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun homeScreen(navController: NavController, userRepository: UserRepository)
{
    val navNum by remember {
        mutableStateOf(0)
    }
    val userID = userRepository.getCurrentUserId()
    val exampleTaskId = userRepository.fetchUserTasks(userID).firstOrNull()?.id ?: -1L
    val userId = navController.previousBackStackEntry?.arguments?.getLong("userId") ?: -1L

    val username = userRepository.getUserName()
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
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .clickable(onClick = {
                            navController.navigate(Screens.profile)
                        })
                )
                {

                        Icon(painter = painterResource(
                            id =R.drawable.userprofile ) ,
                            contentDescription ="" ,
                            tint = Color(0xFF6368D9)
                            , modifier = Modifier
                                .background(
                                    shape =
                                    RoundedCornerShape(40.dp),
                                    color = Color.Transparent
                                )
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
                                letterSpacing = 0.28.sp,
                            )
                        )}
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
//                    navController.navigate("createTask/-1")
                    navController.navigate("${Screens.createTask}?userId = $userId" )
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
                        navController.navigate(Screens.homeScreen)
                    }) {
                    Icon(
                        painterResource(id = R.drawable.home),
                        contentDescription = "",
                        tint = if (navNum == 0) Color(0xFF020CDF) else Color(0xFFA0A4FF),
                        modifier = Modifier.size(30.dp)
                    )
                }
                IconButton(
                    onClick = {
        if (exampleTaskId != -1L) {
            navController.navigate("notification/$exampleTaskId")
        } else {
            Log.d("home navigation", "else block of home notification")
            navController.navigate(Screens.notification)
        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.notification),
                        contentDescription = "",
                        tint = if (navNum == 1) Color(0xFF020CDF) else Color(0xFFA0A4FF),

                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(
                    onClick = {
                        navController.navigate(Screens.profile)
                    }) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "",
                        tint = if (navNum == 2) Color(0xFF020CDF) else Color(0xFFA0A4FF),
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

                        CategoryCard(category = "Home", iconResourceId = R.drawable.home_category) {
                            navController.navigate(Screens.homeCategory)
                        }

                        CategoryCard(category = "Personal Projects",
                            iconResourceId = R.drawable.personal) {
                            navController.navigate(Screens.personalProjectsCategory)
                        }

                        CategoryCard("Other ", R.drawable.folder) {
                            navController.navigate(Screens.otherCategory)
                        }

}}} } }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun previewHomeScreen() {
//    homeScreen(rememberNavController())
}