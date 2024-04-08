package com.example.taskify.database

import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
class UserRepository(private val context: Context,private val dbHelper: UserDbHelper) {
    fun registerUser(username:String, email:String, password:String): Boolean
    {

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(UserContract.UserEntry.COLUMN_USERNAME, username)
            put(UserContract.UserEntry.COLUMN_EMAIL, email)
            put(UserContract.UserEntry.COLUMN_PASSWORD, password)
        }

        val newRowId = db?.insert(UserContract.UserEntry.TABLE_NAME,null,values)
        db?.close()
        return newRowId != -1L
    }

    fun loginUser(username: String,email: String, password: String) : Boolean{
        val db = dbHelper.readableDatabase
//        val projection = arrayOf(BaseColumns._ID)
        val projection = arrayOf(UserContract.UserEntry.COLUMN_EMAIL)

        val selection = "${UserContract.UserEntry.COLUMN_USERNAME} = ? AND ${UserContract.UserEntry.COLUMN_EMAIL} = ? AND ${UserContract.UserEntry.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(username,email, password)

        val cursor = db.query(
            UserContract.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val success = cursor.count > 0
        cursor.close()
        db.close()
        return success
    }

    fun saveUserName(username: String){

        val sharedPreferences :SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }

    fun getUserName():String{
        val sharedPreferences:SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "") ?: ""
    }

    fun insertTask(task: Task): Boolean {
        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_TITLE, task.title)
            put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.description)
            put(TaskContract.TaskEntry.COLUMN_DATE, task.date)
            put(TaskContract.TaskEntry.COLUMN_START_TIME, task.startTime)
            put(TaskContract.TaskEntry.COLUMN_END_TIME, task.endTime)
            put(TaskContract.TaskEntry.COLUMN_CATEGORY, task.category)
        }

        val newRowId = db.insert(TaskContract.TaskEntry.TABLE_NAME, null, values)
        db.close()

        return newRowId != -1L
    }

    fun getAllTasks(): List<Task> {
        val tasks = mutableListOf<Task>()
        val db = dbHelper.readableDatabase

        val cursor = db.query(
            TaskContract.TaskEntry.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TITLE))
                val description = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DATE))
                val startTime = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_START_TIME))
                val endTime = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_END_TIME))
                val category = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_CATEGORY))

                val task = Task(id, title, description, date, startTime, endTime, category)
                tasks.add(task)
            }
        }

        cursor.close()
        db.close()

        return tasks
    }
    fun getTasksByCategory(category: String): List<Task> {
        val tasks = mutableListOf<Task>()
        val db = dbHelper.readableDatabase

        val selection = "${TaskContract.TaskEntry.COLUMN_CATEGORY} = ?"
        val selectionArgs = arrayOf(category)

        val cursor = db.query(
            TaskContract.TaskEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TITLE))
                val description = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DATE))
                val startTime = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_START_TIME))
                val endTime = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_END_TIME))
                val taskCategory = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_CATEGORY))

                val task = Task(id, title, description, date, startTime, endTime, taskCategory)
                tasks.add(task)
            }
        }

        cursor.close()
        db.close()

        return tasks
    }
}