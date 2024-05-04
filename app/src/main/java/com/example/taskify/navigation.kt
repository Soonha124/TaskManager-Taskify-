package com.example.taskify

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
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
    const val userAccountInfo = "userAccountInfo"


}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(context: Context){
    val navController = rememberNavController()
    val userRepository = UserRepository(context = LocalContext.current,
        UserDbHelper(context))

    val userId = userRepository.getCurrentUserId() ?: -1L
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
                navController = navController, userRepository = userRepository)
        }
        composable(Screens.loginScreen)
        {
            loginScreen(
                navController = navController, userRepository = userRepository)
        }



        composable(Screens.homeScreen)
        {
            homeScreen(navController = navController, userRepository,
//                onBackPressedDispatcher = OnBackPressedDispatcher()
            )
        }

//        composable(Screens.createTask)
//        { backStackEntry ->
//            val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
//            createTask(navController = navController, userRepository = userRepository, userId = userId, taskId = taskId)
//
//        }

        composable(Screens.createTask)
        {
            createTask(navController = navController,
                userRepository)
        }

        composable(Screens.calender)
        {
            calender(navController = navController)
        }

        composable(Screens.profile)
        {
            profile(navController = navController,
                userRepository = userRepository)
        }

        composable(Screens.notification)
        {
            notification(navController = navController)
        }

        composable(Screens.workCategory){
            workCategory(navController = navController,
                userRepository)
        }

        composable(Screens.studyCategory){
            studyCategory(navController = navController,
                userRepository)
        }

        composable(Screens.otherCategory){
            otherCategory(navController = navController,
                userRepository)
        }

        composable(Screens.userAccountInfo){
            userAccountInfo(navController = navController,
                userRepository = userRepository)
        }
    }
}
