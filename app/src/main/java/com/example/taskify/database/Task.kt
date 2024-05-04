package com.example.taskify.database

data class Task(
    val id: Long? = null,
    val title: String ,
    val description: String,
    val date: String ,
    val startTime: String ,
    val endTime: String ,
    val category: String
)

//data class User(
//    val id: Long,
//    val username: String,
//    val email: String,
//    val password: String
//)
//sealed class LoginResult {
//    data class Success(val userId: Long) : LoginResult()
//    data class Error(val reason: LoginResult.ErrorReason) : LoginResult()
//
//    enum class ErrorReason {
//        EmptyFields,
//        InvalidCredentials,
//    }
//}