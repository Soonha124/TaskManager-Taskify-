package com.example.taskify

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavController
import com.example.taskify.database.Task
import com.example.taskify.database.UserRepository
import com.example.taskify.components.Tags
import java.util.*

class TaskAlarm : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("NOTIFICATION_TITLE")
        val description = intent.getStringExtra("NOTIFICATION_CONTENT")
        val taskId = intent.getLongExtra("taskId", -1)
        try {
            if (description != null && title != null) {
                    showNotification(
                        context, title, description, taskId
                    )
            }
        }
        catch (
            ex: Exception,
        ){
            Log.d("Receive Ex", "onReceive: ${ex.printStackTrace()}")
        }
    }
}
@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.O)
private fun showNotification(context: Context, title: String, desc: String, taskId: Long) {
    val intent = Intent(context, MainActivity::class.java ).apply {
        putExtra("navDestination", Screens.notification)
        putExtra("taskId", taskId)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent = PendingIntent.getActivity(context, taskId.toInt(), intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

    val channelId = "message_channel"
    val soundUri = Uri.parse("android.resource://${context.packageName}/raw/notification_music")

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(channelId, "message_name",
            NotificationManager.IMPORTANCE_DEFAULT).apply {
            setSound(soundUri, Notification.AUDIO_ATTRIBUTES_DEFAULT)
        }
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    val notification = NotificationCompat.Builder(context, channelId)
        .setContentTitle(title)
        .setContentText(desc)
        .setSmallIcon(R.drawable.notify_task)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .setSound(soundUri)
        .build()


    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
    ) {

//        ActivityCompat.requestPermissions(
//            (context as Activity),
//            arrayOf(Manifest.permission.POST_NOTIFICATIONS),
//            2
//        )
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
        return

    }
    NotificationManagerCompat.from(context).notify(taskId.toInt(), notification)

}



private fun showTimePicker(context: Context, calendar: Calendar, onTimeSet: () -> Unit) {
    TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            onTimeSet()
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    ).show()
}


private fun showDatePicker(context: Context, calendar: Calendar, onDateSet: () -> Unit) {
    DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            onDateSet()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}
@SuppressLint("ScheduleExactAlarm")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createTask(navController: NavController, userRepository: UserRepository
) {
    val userId = userRepository.getCurrentUserId()


    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }
    var selectedCategory by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current

    var date  by remember {
        mutableStateOf("")
    }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }


    val calendarForStartTime = Calendar.getInstance()
    val calendarForEndTime = Calendar.getInstance()


    Scaffold(modifier = Modifier.padding(
        top = 10.dp,
        bottom = 10.dp),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(100.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            tint = Color(0xFF6368D9),
                            modifier = Modifier
                                .size(30.dp)
                                .clickable(onClick = {
                                    navController.navigate(Screens.homeScreen)
                                })
                        )
                        Text(
                            text = "Create Task",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF6368D9),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.28.sp,
                            )
                        )
                    }
                })
        })
    { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(all = 20.dp)
        ) {
            OutlinedTextField(modifier = Modifier.fillMaxWidth(),
                placeholder =
                {
                    Text(
                        text = "Title",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp
                        )
                    )
                },
                value = title,
                onValueChange = {
                    title = it
                },
                leadingIcon = {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "",
                        tint = Color(0xFF6368D9)
                    )
                })
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .border(
                        border = BorderStroke(1.dp, Color.Gray),
                        shape = RoundedCornerShape(2.dp)
                    )
                    .clickable(
                        onClick = {
                            showDatePicker(context, calendarForStartTime)
                            {
                                date = formatDate(calendarForStartTime)
                            }
                        }
                    ))
            {
                Icon(
                    painter = painterResource(
                        id = R.drawable.date),
                    contentDescription = "date",
                    tint = Color.Blue,
                    modifier = Modifier
                        .size(25.dp)
                        .padding(start = 5.dp))

                    Text(
                        text = "Date : ",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    )

                Text(
                    text = date,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF6368D9),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.28.sp,
                    ))
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            )
            {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border(
                            border = BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(2.dp)
                        )
                        .clickable(
                            onClick = {
                                showTimePicker(context, calendarForStartTime) {
                                    startTime = formatTime(calendarForStartTime)
                                }
                            }
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "clock",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 10.dp)
                    )

                    Text(
                        text = "Start : ",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp
                        )
                    )

                    Text(
                        text = startTime,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    )

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp)
                        .border
                            (
                            border = BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(2.dp)
                        )
                        .clickable {
                            showTimePicker(context, calendarForEndTime) {
                                endTime = formatTime(calendarForEndTime)
                            }
                        }
                )
                {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "clock",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = "End : ",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp
                        )
                    )
                    Text(
                        text = endTime ,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    ) }
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            )
            {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "Select a Category",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    )
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)
                {

                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    )
                    {

                        item {
                            Tags(
                                label = "Work",
                                modifier = Modifier
                                    .width(115.dp)
                                    .clickable {
                                        selectedCategory = "Work"
                                    },
                                selected = selectedCategory == "Work",
                                onTagClicked = { selectedCategory = "Work" }
                            )
                        }
                        item{
                            Tags(label = "Study",
                                modifier = Modifier
                                    .width(115.dp)
                                    .clickable { selectedCategory = "Study" },
                                selected = selectedCategory == "Study",
                                onTagClicked = { selectedCategory = "Study" }
                            )
                        }
                        item {
                            Tags(
                                label = "Home",
                                modifier = Modifier
                                    .width(115.dp)
                                    .clickable {
                                        selectedCategory = "Home"
                                    },
                                selected = selectedCategory == "Home",
                                onTagClicked = { selectedCategory = "Home" }
                            )
                        }
                        item {
                            Tags(
                                label = "Personal Project",
                                modifier = Modifier
                                    .width(215.dp)
                                    .clickable {
                                        selectedCategory = "Personal Project"
                                    },
                                selected = selectedCategory == "Personal Project",
                                onTagClicked = { selectedCategory = "Personal Project" }
                            )
                        }
                        item {
                            Tags(label = "Other",
                                modifier = Modifier
                                    .width(115.dp)
                                    .clickable { selectedCategory = "Other" },
                                selected = selectedCategory == "Other",
                                onTagClicked = { selectedCategory = "Other" }
                            )
                        }
                    }
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                maxLines = 200,
                placeholder =
                {
                    Text(
                        text = "Description",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    )
                },
                value = description,
                onValueChange = {
                    description = it
                })
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            )
            {

                ElevatedButton(
                    shape = RoundedCornerShape(topEnd = 30.dp,
                        bottomStart = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF6368D9)
                    ),
                    onClick =
                    {
                        if(validateDateTime(calendarForStartTime, calendarForEndTime)){
                            if (selectedCategory.isNotEmpty() && title.isNotEmpty()
                                && date.isNotEmpty() && startTime.isNotEmpty()
                                && endTime.isNotEmpty()
                                && description.isNotEmpty()) {
                                userRepository.insertTask(
                                    task = Task(
                                        id = null,
                                        title = title,
                                        date = formatDate(calendarForStartTime),
                                        startTime =formatDate(calendarForStartTime),
                                        endTime = formatDate(calendarForEndTime),
                                        category = selectedCategory,
                                        description = description
                                    ), userId = userId
                                )
                                scheduleNotification(
                                    context = context,
                                    title = title,
                                    content = "Do your Task: $description",
                                    calendar = calendarForStartTime,
                                    requestCode = 1)

                                scheduleNotification(
                                    context = context,
                                    title = title,
                                    content ="Hurry Up: $description",
                                    calendar = calendarForEndTime,
                                    requestCode = 2)

                                Toast.makeText(context, "Task Saved", Toast.LENGTH_SHORT).show()
                                navController.popBackStack()
                            }
                            else
                            {
                                Toast.makeText(context, "All fields are required",
                                    Toast.LENGTH_SHORT).show()
                            }

                        }
                        else{
                            Toast.makeText(context, "Start time must be before end time",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                ){
                    Text(text = "Save",
                        style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.28.sp,
                    ))
                }
            }}}

}


private fun formatDate(calendar: Calendar): String {
    val dateFormat = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return dateFormat.format(calendar.time)
}

private fun formatTime(calendar: Calendar): String {
    val timeFormat = java.text.SimpleDateFormat("hh:mm a", java.util.Locale.getDefault())
    return timeFormat.format(calendar.time)
}

private fun validateDateTime(startCalendar: Calendar, endCalendar: Calendar): Boolean {
    return startCalendar.before(endCalendar) && startCalendar.timeInMillis != endCalendar.timeInMillis
}

@SuppressLint("ScheduleExactAlarm")
private fun scheduleNotification(context: Context, title: String,
                                 content: String, calendar: Calendar,
                                 requestCode:Int) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, TaskAlarm::class.java).apply {
        putExtra("NOTIFICATION_TITLE", title)
        putExtra("NOTIFICATION_CONTENT", content)
    }
    val flags = PendingIntent.FLAG_UPDATE_CURRENT or (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_MUTABLE else 0)
    val pendingIntent = PendingIntent.getBroadcast(context,requestCode,intent, flags )
    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun previewCreateTask()
{
//    createTask(rememberNavController())
}