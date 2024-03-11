package com.example.taskify

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

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
@Composable
fun AppNavigation(){
    val navController = rememberNavController()

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
                navController = navController)
        }
//        signUp(
//            navController = navController.navigateToSingleTop(
//                Screens.signUp
//            )
//        )
        composable(Screens.loginScreen)
        {
            loginScreen(
                navController = navController)
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