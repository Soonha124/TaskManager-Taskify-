package com.example.taskify.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.database.sqlite.SQLiteException
import android.provider.BaseColumns
import android.util.Log

class UserRepository(private val context: Context,private val dbHelper: UserDbHelper)
{
   fun registerUser(username:String, email:String, password:String): Boolean
    {

        val db = dbHelper.writableDatabase
        try {
            db.beginTransaction()
            val values = ContentValues().apply {
                put(UserContract.UserEntry.COLUMN_USERNAME, username)
                put(UserContract.UserEntry.COLUMN_EMAIL, email)
                put(UserContract.UserEntry.COLUMN_PASSWORD, password)
            }

            val newRowId = db.insertOrThrow(UserContract.UserEntry.TABLE_NAME, null, values)
            if (newRowId == -1L) {
                return false
            }

            saveUserId(newRowId)
            saveUserEmail(email)
            saveUserPassword(password)
            db.setTransactionSuccessful()
            return true
        } catch (e: Exception) {
            Log.e("UserRepository", "Error registering user", e)
            return false
        } finally {
            db.endTransaction()
            db.close()
        }
//        val values = ContentValues().apply {
//            put(UserContract.UserEntry.COLUMN_USERNAME, username)
//            put(UserContract.UserEntry.COLUMN_EMAIL, email)
//            put(UserContract.UserEntry.COLUMN_PASSWORD, password)
//        }
//
//        val newRowId = db?.insert(UserContract.UserEntry.TABLE_NAME,null,values)
//        db?.close()
//
//        if (newRowId != -1L) {
//            if (newRowId != null) {
//                saveUserId(newRowId)
//            }
//            saveUserEmail(email)
//            saveUserPassword(password)
//            return true
//        }
//        return false
    }

    @SuppressLint("Range")
    fun loginUser(username: String, email: String, password: String) : Boolean {
        val db = dbHelper.readableDatabase
//        val projection = arrayOf(BaseColumns._ID)
//        val projection = arrayOf(BaseColumns._ID, UserContract.UserEntry.COLUMN_EMAIL)

        val selection = "${UserContract.UserEntry.COLUMN_USERNAME} = ? AND ${UserContract.UserEntry.COLUMN_EMAIL} = ? AND ${UserContract.UserEntry.COLUMN_PASSWORD} = ?"
        val selectionArgs = arrayOf(username,email, password)

        val cursor = db.query(
            UserContract.UserEntry.TABLE_NAME,
            arrayOf(BaseColumns._ID),
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        return if (cursor.moveToFirst()) {
            val userId = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID))
            saveUserId(userId)
            saveUserEmail(email)
            saveUserPassword(password)
            cursor.close()
            db.close()
            true
        } else {
            cursor.close()
            db.close()
            false
        }
    }
//
//        val success = cursor.count > 0
//
//        if (success && cursor.moveToFirst()) {
//            val userId = cursor.getLong(cursor.getColumnIndex(BaseColumns._ID))
//            saveUserId(userId)
//            saveUserEmail(email)
//            Log.d("UserRepository", "Logged in and saved user ID: $userId")
//        } else {
//            Log.d("UserRepository", "Login failed or user not found")
//        }
//        cursor.close()
//        db.close()
//        return success
//    }

//    private fun saveUserId(userId: Long) {
//        val sharedPreferences: SharedPreferences =
//            context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
//        val editor: SharedPreferences.Editor = sharedPreferences.edit()
//        editor.putLong("userId", userId)
//        editor.apply()
//    }
fun logoutUser() {
    val sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.clear()
    editor.apply()
}

    fun saveUserName(username: String){

        val sharedPreferences :SharedPreferences = context.getSharedPreferences("user_pref",
            Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.apply()
    }

    fun getUserName():String{
        val sharedPreferences:SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "") ?: ""
    }

    fun saveUserEmail(userEmail:String) {
        val sharedPreferences:SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val editor :SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("userEmail", userEmail)
        editor.apply()
    }
    fun getUserEmail(): String? {
        val sharedPreferences:SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        return sharedPreferences.getString("userEmail", null)
    }
    private fun saveUserPassword(password: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("password", password)
        editor.apply()
    }
    fun getUserPassword(): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        return sharedPreferences.getString("password", null)
    }
    fun saveUserId(userId: Long) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putLong("userId", userId)
        editor.apply()
    }
//    fun getCurrentUserId(): Long? {
//        val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
//        val userId = sharedPreferences.getLong("userId", -1)
//        Log.d("UserRepository", "Current user ID: $userId")
//        return if (userId == -1L) null else userId
//    }
fun getCurrentUserId(): Long {
    val sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("userId", -1)
    Log.d("SessionManagement", "Retrieved User ID: $userId")
    return userId
}
//    fun getCurrentUser(): User? {
//        val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
//        val userId = sharedPreferences.getLong("userId", -1)
//        if (userId != -1L) {
//            val username = sharedPreferences.getString("username", "") ?: ""
//            val email = sharedPreferences.getString("email", "") ?: ""
//            val password = sharedPreferences.getString("password", "") ?: ""
//            return User(userId, username, email, password)
//        }
//        return null
//    }
fun getUserId(): Long {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
    return sharedPreferences.getLong("userId", -1)
}
    fun insertTask(userId: Long, task: Task): Boolean {
        if (userId == -1L) {
            Log.d("TaskInsertion", "Task ${task.title} inserted for User ID $userId")
            Log.e("TaskInsertion", "Invalid user ID: $userId")
            return false
        }

        val db = dbHelper.writableDatabase
        try {
            db.beginTransaction()
            val values = ContentValues().apply {
                put(TaskContract.TaskEntry.COLUMN_USER_ID, userId)
                put(TaskContract.TaskEntry.COLUMN_TITLE, task.title)
                put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.description)
                put(TaskContract.TaskEntry.COLUMN_DATE, task.date)
                put(TaskContract.TaskEntry.COLUMN_START_TIME, task.startTime)
                put(TaskContract.TaskEntry.COLUMN_END_TIME, task.endTime)
                put(TaskContract.TaskEntry.COLUMN_CATEGORY, task.category)
            }

            val newRowId = db.insertOrThrow(TaskContract.TaskEntry.TABLE_NAME, null, values)
            if (newRowId == -1L) {
                return false
            }
            db.setTransactionSuccessful()
            return true
        } catch (e: SQLiteException) {
            Log.e("TaskInsertion", "Error inserting task", e)
            return false
        } finally {
            db.endTransaction()
            db.close()
        }

//        return newRowId != -1L
    }
//    fun insertTask(userId: Long, task: Task): Boolean
//    {
//        val db = dbHelper.writableDatabase
//        if (userId == -1L) {
//            Log.e("TaskInsertion", "Invalid user ID: $userId")
//            return false
//        }
////        val userId = getCurrentUserId() ?: return false
//        Log.d("TaskInsertion", "Inserting task for user ID: $userId")
//        Log.d("TaskInsertion", "Task details: $task")
//
//        val values = ContentValues().apply {
//            put(TaskContract.TaskEntry.COLUMN_ID, userId)
//            put(TaskContract.TaskEntry.COLUMN_TITLE, task.title)
//            put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.description)
//            put(TaskContract.TaskEntry.COLUMN_DATE, task.date)
//            put(TaskContract.TaskEntry.COLUMN_START_TIME, task.startTime)
//            put(TaskContract.TaskEntry.COLUMN_END_TIME, task.endTime)
//            put(TaskContract.TaskEntry.COLUMN_CATEGORY, task.category)
//        }
//
//        val newRowId = db.insert(TaskContract.TaskEntry.TABLE_NAME,
//            null, values)
//        db.close()
//        Log.d("TaskInsertion", "Task inserted, newRowId: $newRowId")
//
//        val success = newRowId != -1L
//        if (success) {
//            Log.d("TaskInsertion", "Task inserted successfully with ID: $newRowId")
//        } else {
//            Log.e("TaskInsertion", "Failed to insert task")
//        }
//
//        return newRowId != -1L
//    }


    fun getTasksByCategory(category: String): List<Task> {
        val userId = getCurrentUserId()
        Log.d("Debug", "Fetching tasks for User ID:" +
                " $userId and category: $category")
        if (userId == -1L) return emptyList()

//    val userId = getCurrentUserId()
//    if (userId != -1L) {
        val db = dbHelper.readableDatabase

        val selection = "${TaskContract.TaskEntry.COLUMN_CATEGORY} = ? AND ${TaskContract.TaskEntry.COLUMN_USER_ID} = ?"
        val selectionArgs = arrayOf(category, userId.toString())
        Log.d("SelectionQuery", "Selection: $selection, SelectionArgs: ${selectionArgs.joinToString()}")

        val cursor = db.query(
            TaskContract.TaskEntry.TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        val tasks = mutableListOf<Task>()


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
                Log.d("Debug", "Loaded task: ${task.title} for User ID: $userId")
            }
        }

        cursor.close()
        db.close()

        return tasks
    }

    fun clearUserData() {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getLong("userId", -1)

        sharedPreferences.edit().clear().apply()

        if (userId != -1L) {
            clearUserTaskData(userId)
        }
    }

    private fun clearUserTaskData(userId: Long) {
        val db = dbHelper.writableDatabase
        db.delete(TaskContract.TaskEntry.TABLE_NAME, "${TaskContract.TaskEntry.COLUMN_USER_ID} = ?", arrayOf(userId.toString()))
        db.close()
    }


    fun getTaskById(id: Long): Task? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            TaskContract.TaskEntry.TABLE_NAME,
            null,
            "${TaskContract.TaskEntry.COLUMN_ID} = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        val task = if (cursor.moveToFirst()) Task(
            id = cursor.getLong(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_ID)),
            title = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TITLE)),
            description = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESCRIPTION)),
            date = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DATE)),
            startTime = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_START_TIME)),
            endTime = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_END_TIME)),
            category = cursor.getString(cursor.getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_CATEGORY))
        ) else null
        cursor.close()
        db.close()
        return task
    }

    fun updateTask(task: Task): Boolean {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(TaskContract.TaskEntry.COLUMN_TITLE, task.title)
            put(TaskContract.TaskEntry.COLUMN_DESCRIPTION, task.description)
            put(TaskContract.TaskEntry.COLUMN_DATE, task.date)
            put(TaskContract.TaskEntry.COLUMN_START_TIME, task.startTime)
            put(TaskContract.TaskEntry.COLUMN_END_TIME, task.endTime)
            put(TaskContract.TaskEntry.COLUMN_CATEGORY, task.category)
        }
        val selection = "${TaskContract.TaskEntry.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(task.id.toString())

        val count = db.update(
            TaskContract.TaskEntry.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )
        db.close()
        return count > 0
    }



    fun fetchUserTasks(userId: Long): List<Task> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            TaskContract.TaskEntry.TABLE_NAME,
            null,
            "${TaskContract.TaskEntry.COLUMN_USER_ID} = ?",
            arrayOf(userId.toString()),
            null,
            null,
            null
        )
        val tasks = mutableListOf<Task>()
        with(cursor) {
            while (moveToNext()) {
                val id = getLong(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_ID))
                val title = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_TITLE))
                val description = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DESCRIPTION))
                val date = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_DATE))
                val startTime = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_START_TIME))
                val endTime = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_END_TIME))
                val category = getString(getColumnIndexOrThrow(TaskContract.TaskEntry.COLUMN_CATEGORY))
                tasks.add(Task(id, title, description, date, startTime, endTime, category))
            }
        }
        cursor.close()
        db.close()
        return tasks
    }
}

