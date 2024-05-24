package com.example.taskify

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.taskify.ViewModel.NavigationViewModel
import com.example.taskify.ViewModel.NavigationViewModelFactory
import com.example.taskify.database.UserDbHelper
import com.example.taskify.database.UserRepository
import com.example.taskify.ui.theme.TaskifyTheme
import androidx.compose.runtime.RememberObserver
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationViewModel: NavigationViewModel = viewModel(
                factory =
                NavigationViewModelFactory(applicationContext)
            )
            val userRepository = UserRepository(
                applicationContext,
                UserDbHelper(applicationContext)
            )
            val navController = rememberNavController()

            TaskifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                )
                {
                    val isLoggedIn = userRepository.isLoggedIn()

                    LaunchedEffect(key1 = isLoggedIn) {
                        launch {
                            if (isLoggedIn) {
                                navController.navigate(Screens.homeScreen)
                            } else {
                                navController.navigate(Screens.splashScreen)
                            }
                        }

                    }
                        AppNavigation(navController, navigationViewModel, userRepository)

                }
            }
        }
    }

}





