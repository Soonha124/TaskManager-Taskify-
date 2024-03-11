package com.example.taskify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.navigation.compose.rememberNavController

@Composable
fun splashScreen(navController: NavController){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp),
        modifier = Modifier
            .padding(all = 50.dp)
//        .fillMaxSize()
    )
    {
    Image(painter = painterResource(id = R.drawable.splash),
        contentDescription = "",
        modifier = Modifier.size(400.dp))
        Text(
            text = "Manage your task,\nquickly.",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight(800),
                color = Color(0xFF6368D9),

                textAlign = TextAlign.Center,
                letterSpacing = 0.48.sp,
            )
        )
        Box(contentAlignment = Alignment.Center,

            modifier = Modifier.background(color = Color(0xFF6368D9),
                shape = RoundedCornerShape(30.dp))

        ) {
            IconButton(onClick = {
                navController.navigate(Screens.signUp)
            }) {
                Icon(modifier = Modifier.size(24.dp),painter = painterResource(id = R.drawable.vector),
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        }

    }
}
@Preview
@Composable
fun previewSplashScreen(){
    splashScreen(rememberNavController())
}