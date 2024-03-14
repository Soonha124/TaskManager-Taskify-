package com.example.taskify

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskify.database.UserDbHelper
import com.example.taskify.database.UserRepository

object Screens
{
    const val splashScreen = "splashScreen"
    const val loginScreen = "loginScreen"
    const val signUp = "signUp"
    const val homeScreen = "homeScreen"
    const val calender = "calender"
    const val createTask = "createTask"
    const val profile = "profile"
    const val notification = "notification"
    const val workCategory = "workCategory"
    const val studyCategory = "studyCategory"
    const val otherCategory = "otherCategory"

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val userRepository = UserRepository(UserDbHelper(LocalContext.current))

    NavHost(navController = navController,
        startDestination = Screens.splashScreen)
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
//        signUp(
//            navController = navController.navigateToSingleTop(
//                Screens.signUp
//            )
//        )
        composable(Screens.loginScreen)
        {
            loginScreen(
                navController = navController, userRepository = userRepository)
        }

        composable(Screens.homeScreen)
        {
            homeScreen(navController = navController)
        }

        composable(Screens.createTask)
        {
            createTask(navController = navController)
        }

        composable(Screens.calender)
        {
            calender(navController = navController)
        }

        composable(Screens.profile)
        {
            profile(navController = navController)
        }

        composable(Screens.notification)
        {
            notification(navController = navController)
        }

        composable(Screens.workCategory){
            workCategory(navController = navController)
        }
        
        composable(Screens.studyCategory){
            studyCategory(navController = navController)
        }
        
        composable(Screens.otherCategory){
            otherCategory(navController = navController)
        }
    }
}

fun NavController.navigateToSingleTop(route:String){
    navigate(route){
        popUpTo(graph.findStartDestination().id){
            saveState = true
        }

        launchSingleTop = true
        restoreState = true
    }
}