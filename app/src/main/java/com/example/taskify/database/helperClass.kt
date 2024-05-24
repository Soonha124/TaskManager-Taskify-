package com.example.taskify.database

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class UserDbHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,
    null, DATABASE_VERSION) {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(db: SQLiteDatabase)
    {
        val User_ENTRIES = "CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (" +
                "${UserContract.UserEntry.USER_ID} INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "${UserContract.UserEntry.COLUMN_USERNAME} TEXT," +
                "${UserContract.UserEntry.COLUMN_EMAIL} TEXT," +
                "${UserContract.UserEntry.COLUMN_PASSWORD} TEXT)"

        db.execSQL(User_ENTRIES)

        val TASKS_TABLE_CREATE = "CREATE TABLE ${TaskContract.TaskEntry.TABLE_NAME} (" +
                "${TaskContract.TaskEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${TaskContract.TaskEntry.COLUMN_USER_ID} INTEGER,"+
                "${TaskContract.TaskEntry.COLUMN_TITLE} TEXT," +
                "${TaskContract.TaskEntry.COLUMN_DESCRIPTION} TEXT," +
                "${TaskContract.TaskEntry.COLUMN_DATE} TEXT," +
                "${TaskContract.TaskEntry.COLUMN_START_TIME} TEXT," +
                "${TaskContract.TaskEntry.COLUMN_END_TIME} TEXT," +
                "${TaskContract.TaskEntry.COLUMN_CATEGORY} TEXT)"
        db.execSQL(TASKS_TABLE_CREATE)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
    }

    companion object{
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Tasks.db"
    }

}

object UserContract {
    object UserEntry : BaseColumns{
        const val TABLE_NAME = "users"
        const val USER_ID  = BaseColumns._ID
        const val COLUMN_USERNAME = "username"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
    }
}
object TaskContract {
    object TaskEntry : BaseColumns {
        const val TABLE_NAME = "tasks"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_USER_ID = "userId"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_DATE = "date"
        const val COLUMN_START_TIME = "start_time"
        const val COLUMN_END_TIME = "end_time"
        const val COLUMN_CATEGORY = "category"
    }
}