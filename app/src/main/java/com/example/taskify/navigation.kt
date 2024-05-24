package com.example.taskify

import android.app.Activity
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.taskify.ViewModel.NavigationViewModel
import com.example.taskify.database.UserRepository

object Screens {
    const val splashScreen = "splashScreen"
    const val loginScreen = "loginScreen"
    const val signUp = "signUp"
    const val homeScreen = "homeScreen"
    const val createTask = "createTask"
    const val profile = "profile"
    const val notification = "notification"
    const val workCategory = "workCategory"
    const val studyCategory = "studyCategory"
    const val otherCategory = "otherCategory"
    const val userAccountInfo = "userAccountInfo"
    const val homeCategory = "homeCategory"
    const val personalProjectsCategory = "personalProjectsCategory"


}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(navController: NavHostController,
                  navigationViewModel: NavigationViewModel,
                  userRepository: UserRepository)
{
    val context = LocalContext.current

    var lastBackPress by remember { mutableStateOf(0L) }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    var showExitDialog by remember { mutableStateOf(false) }
    val screens: List<String> = listOf(
        Screens.createTask,
        Screens.profile,
        Screens.notification,
        Screens.userAccountInfo,
        Screens.workCategory,
        Screens.studyCategory,
        Screens.otherCategory,
        Screens.homeScreen,
        Screens.splashScreen
    )

    BackHandler(onBack = {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastBackPress < 2000) {
            Log.d("System Back Button", "Back Button pressed, showing dialog")
            showExitDialog = true
        } else {
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            lastBackPress = currentTime
        }
    })

//        val currentTime = System.currentTimeMillis()
//
//        if (currentRoute.toString() in (Screens.signUp)
//            && currentTime - lastBackPress < 2000 ) {
//            showExitDialog = true
//            Log.d("back handler",
//                "if block executed in SignUp Screen")
//
//            Log.d("back handler", "Not a critical screen SignUp Screen")
//        }
//
//
//        else if(currentRoute.toString() in (Screens.loginScreen)
//            && currentTime - lastBackPress < 2000 ) {
//        showExitDialog = true
//        Log.d("back handler",
//            "if block executed in Login Screen")
//
//        Log.d("back handler", "Not a critical screen Login Screen")
//    }
//
//        else if(currentRoute.toString() in (Screens.homeScreen)
//            && currentTime - lastBackPress < 2000 ) {
//                showExitDialog = true
//                Log.d("back handler",
//                    "if block executed in Home Screen")
//
//                Log.d("back handler", "Not a critical screen Home Screen")
//            }
//
//        else if(currentRoute.toString() in (Screens.createTask)
//            && currentTime - lastBackPress < 2000 ) {
//                showExitDialog = true
//                Log.d("back handler",
//                    "if block executed in createTask Screen")
//
//            Log.d("back handler", "Not a critical screen createTask Screen")
//        }
//
//        else if(currentRoute.toString() in (Screens.notification)
//            && currentTime - lastBackPress < 2000 ) {
//            showExitDialog = true
//            Log.d("back handler",
//                "if block executed in Notification Screen")
//
//            Log.d("back handler", "Not a critical screen Notification Screen")
//        }
//
//
//        else if(currentRoute.toString() in (Screens.profile)
//            && currentTime - lastBackPress < 2000 ) {
//            showExitDialog = true
//            Log.d("back handler",
//                "if block executed in Profile Screen")
//
//            Log.d("back handler", "Not a critical screen Profile Screen")
//        }
//
//        else if(currentRoute.toString() in (Screens.workCategory)
//            && currentTime - lastBackPress < 2000 ) {
//            showExitDialog = true
//            Log.d("back handler",
//                "if block executed in Work Screen")
//
//            Log.d("back handler", "Not a critical screen Work Screen")
//        }
//
//        else if(currentTime == lastBackPress)
//        {
//            Toast.makeText(context, "Press Again", Toast.LENGTH_SHORT).show()
//        }



    if (showExitDialog) {
        AlertDialog(onDismissRequest = {
            showExitDialog = false
        },
            title = { Text(text = "Confirm Exit") },
            text = { Text(text = "Are you sure you want to Exit?") },
            confirmButton = {
                Button(onClick = {
                    showExitDialog = false
                    (context as? Activity)?.finish()
                }) {
                    Text(text = "Yes")
                }
            },
            dismissButton = {
                Button(onClick = {
                    showExitDialog = false
                })
                {
                    Text(text = "No")
                }
            })
    }

    NavHost(
        navController = navController,
        startDestination =
        navigationViewModel.lastScreen ?: Screens.splashScreen
    )
    {
        composable(Screens.splashScreen)
        {
            splashScreen(navController = navController)
        }
        composable(Screens.signUp)
        {
            signUp(
                navController = navController, userRepository = userRepository
            )
        }
        composable(Screens.loginScreen)
        {
            loginScreen(
                navController = navController, userRepository = userRepository
            )
        }

        composable(Screens.homeScreen)
        {
            homeScreen(
                navController = navController, userRepository,
            )
        }

        composable(Screens.createTask)
        {
            createTask(
                navController = navController,
                userRepository
            )
        }

        composable(Screens.profile)
        {
            profile(
                navController = navController,
                userRepository = userRepository )
        }

        composable("${Screens.notification}/{taskId}",
            arguments = listOf(navArgument("taskId")
        { type = NavType.LongType })) { backStackEntry ->
            val taskId = backStackEntry.arguments?.getLong("taskId") ?: -1L
            notification(navController = navController, userRepository = userRepository,
                taskId = taskId)
        }

        composable(Screens.workCategory) {
            workCategory(
                navController = navController,
                userRepository
            )
        }

        composable(Screens.homeCategory){
            homeCategory(userRepository = userRepository,
                navController = navController)
        }
        composable(Screens.personalProjectsCategory){
            personalProjectsCategory(userRepository = userRepository,
                navController = navController)
        }
        composable(Screens.studyCategory) {
            studyCategory(
                navController = navController,
                userRepository
            )
        }

        composable(Screens.otherCategory) {
            otherCategory(
                navController = navController,
                userRepository
            )
        }

        composable(Screens.userAccountInfo) {
            userAccountInfo(
                navController = navController,
                userRepository = userRepository
            )
        }
    }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        navigationViewModel.lastScreen = destination.route
    }
}

