package com.example.taskify

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun otherCategory(navController: NavController){
    Scaffold(modifier = Modifier.padding(15.dp),topBar = {
        TopAppBar(title = { Text(text = "Other Categories") })
    }) {innerPadding->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(text = "Nothing to show")
        }
    }
}
@Preview
@Composable
fun otherCategoryPreview() {
    otherCategory(rememberNavController())
}