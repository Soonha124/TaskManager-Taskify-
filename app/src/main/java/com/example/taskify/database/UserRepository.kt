package com.example.taskify.database

import android.content.ContentValues
import android.provider.BaseColumns

class UserRepository(private val dbHelper: UserDbHelper) {
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
}