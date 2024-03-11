package com.example.taskify.ui.theme.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SelectableChipElevation
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskify.R

@Composable
fun bottomNavigationIcons(iconButton: ()-> Unit){
    IconButton(onClick = { /*TODO*/ }) {
    }
}
@Composable
fun ClickableLoginTextComponent(tryingToLogin:Boolean = true,
                                onTextSelected: (String) -> Unit) {
    val initialText = if(tryingToLogin) "Already have an account? " else "Dont have an account? "
    val loginText = if(tryingToLogin)"Log In" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Color(0xFF6368D9))) {
            pushStringAnnotation(
                tag = loginText,
                annotation = loginText
            )
            append(loginText)
        }
    }

    ClickableText(style = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight(400),
        color = Color(0xFF9C98A1),
        textAlign = TextAlign.Center,
        letterSpacing = 0.22.sp,
    ), text = annotatedString,
        onClick = { offset ->
            annotatedString.getStringAnnotations(
                offset, offset
            ).firstOrNull()?.also { span ->
                Log.d(
                    "ClickabletextComponent",
                    "{${span.item}}"
                )
                if (span.item == loginText) {
                    onTextSelected(span.item)
                }
            }
        })
}

@Composable
fun CategoryCard(
    category: String, iconResourceId: Int,
    onClick: ()-> Unit
) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .height(100.dp)
            .padding(8.dp),
        shape = RoundedCornerShape(16.dp) ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color(0xFF6368D9))
                .fillMaxSize()
                .padding(10.dp)
            ,
        ) {
            Icon(painter = painterResource(id = iconResourceId),
                contentDescription = "",
                tint = Color(0xFFCDCFEE),
                modifier = Modifier.padding(5.dp))
            Text(modifier = Modifier.padding(5.dp),
                text = category,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center )
        }
    }
}

@Composable
fun Tags(
    label: String,
    modifier: Modifier
) {
    var selected by remember { mutableStateOf(false) }

    Box(contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                shape = RoundedCornerShape(10.dp),
                color = if (selected) Color(0xFF6368D9)
                else Color(0xFFCFD3FF)
            )
            .clickable { selected = !selected }
            .padding(8.dp)
    ) {
        Text(
            text = label,
            color =  if (selected) Color.White
            else Color(0xFF0015FD)
        )
        if (selected) {
            Icon(
                Icons.Filled.Check,
                contentDescription = "select",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd)
                    .padding(4.dp)
            )
        }
    }
}
