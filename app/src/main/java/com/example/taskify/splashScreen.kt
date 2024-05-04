package com.example.taskify

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun splashScreen(navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .padding(all = 20.dp)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "Diary",
            modifier = Modifier.size(400.dp)
        )
        Text(
            text = "Manage your tasks,\nquickly.",
            style = TextStyle(
                fontSize = 24.sp,
                lineHeight = 30.sp,
                fontFamily = FontFamily.Monospace,
                color = Color(0xFF9C27B0),
                textAlign = TextAlign.Center,
                letterSpacing = 0.48.sp,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedButton(
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 5.dp, pressedElevation = 20.dp
            ),
            shape = RoundedCornerShape(
                topStart = 20.dp,
                bottomEnd = 20.dp
            ),
            colors = ButtonDefaults.buttonColors(
                Color(0xFF6368D9)
            ),
            onClick = {
                navController.navigate(Screens.signUp)
            },
            content = {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.vector),
                    contentDescription = "",
                    tint = Color.White,
                )
            }
        )


    }
}

@Preview
@Composable
fun previewSplashScreen() {
    splashScreen(rememberNavController())
}