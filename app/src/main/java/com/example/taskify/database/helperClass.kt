package com.example.taskify.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class UserDbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val User_ENTRIES = "CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (" +
                 "${UserContract.UserEntry.COLUMN_USERNAME} TEXT," +
                "${UserContract.UserEntry.COLUMN_EMAIL} TEXT," +
                "${UserContract.UserEntry.COLUMN_PASSWORD} TEXT)"

            db.execSQL(User_ENTRIES)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "UserCredentials"
    }
}

object UserContract{
    object UserEntry : BaseColumns{
        const val TABLE_NAME = "users"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
    }
}