package com.example.taskify

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun splashScreen(navController: NavController) {
    val alphaAnim = remember { Animatable(0f) }
    val infiniteTransition = rememberInfiniteTransition("infinite transition")

    val textRepeat = 3

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    LaunchedEffect(key1 = true) {
        alphaAnim.animateTo(
            targetValue = 2f,
            animationSpec = tween(durationMillis = 3000)
        )
        launch {
            delay(3000)
            navController.popBackStack()
            navController.navigate(Screens.signUp)
        }

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(all = 20.dp)
        ) {
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
        }

        repeat(textRepeat)
        {
            Text(
                text = "Taskify",
                color = Color(0xFFC254CE).copy(alpha = alphaAnim.value.coerceIn(0f,1f)),
                fontSize = 34.sp,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                    },
                style = TextStyle(
                    fontFamily = FontFamily.Cursive,
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp
                )
            )
        }

    }
}


@Preview
@Composable
fun previewSplashScreen() {
    splashScreen(rememberNavController())
}