package com.example.taskify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val taskTitle = intent.getStringExtra("task_title") ?: "Task Reminder"
        showNotification(context, taskTitle)
    }

    private fun showNotification(context: Context, taskTitle: String) {
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannelId = "task_notification_channel"
        val soundUri = Uri.parse("android.resource://${context.packageName}/raw/notification_music")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(notificationChannelId,
                "Task Notifications", NotificationManager.IMPORTANCE_HIGH)
                .apply {
                    description = "Channel for Task Notifications"
                    setSound(
                        soundUri,
                        null)
                }
            notificationManager.createNotificationChannel(channel)
        }


        val notificationBuilder = NotificationCompat.Builder(context, notificationChannelId)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Task Reminder")
            .setContentText("You have a task to do: $taskTitle")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_REMINDER)
            .setSound(soundUri)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(taskTitle.hashCode(), notificationBuilder)
    }



}
