package com.example.taskify

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskify.components.categories
import com.example.taskify.database.UserRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun personalProjectsCategory(userRepository: UserRepository,
                             navController: NavController)
{
    Scaffold(modifier = Modifier.padding(15.dp),
        topBar = {
            TopAppBar(title = {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(80.dp,
                        Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color(0xFF6368D9),
                        modifier = Modifier
                            .size(30.dp)
                            .clickable(onClick = {
                                navController.navigate(Screens.homeScreen)
                            })
                    )
                    Text(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        text = "Personal Project",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    )
                }
            })
        }) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)){
            categories(
                userRepository = userRepository,
                category = "Personal Project")

        }
    }
}