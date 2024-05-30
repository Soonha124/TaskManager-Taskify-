package com.example.taskify

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.PowerManager
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.taskify.ViewModel.NavigationViewModel
import com.example.taskify.ViewModel.NavigationViewModelFactory
import com.example.taskify.database.UserDbHelper
import com.example.taskify.database.UserRepository
import com.example.taskify.ui.theme.TaskifyTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
//                            Dispatchers.Main
//                            checkAndShowBatteryOptimizationDialog(this@MainActivity)
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
    private suspend fun checkAndShowBatteryOptimizationDialog(context: Context) {
        withContext(Dispatchers.Main) {
            val packageName = context.packageName
            val pm = context.getSystemService(Context.POWER_SERVICE) as PowerManager
            if (!pm.isIgnoringBatteryOptimizations(packageName)) {
                showBatteryOptimizationDialog(context)
            }
        }
    }

    private suspend fun showBatteryOptimizationDialog(context: Context) {
        withContext(Dispatchers.Main) {
            androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Battery Optimization")
                .setMessage("Please disable battery optimization for this app to ensure notifications work correctly.")
                .setPositiveButton("Go to Settings") { dialog, _ ->
                    val intent = Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
                    context.startActivity(intent)
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}





