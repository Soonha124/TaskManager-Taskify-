package com.example.taskify.database

data class Task(
    val id: Long? = null,
    val title : String,
    val description : String,
    val date : String,
    val startTime : String,
    val endTime : String,
    val category : String
)