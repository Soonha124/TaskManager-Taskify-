package com.example.taskify

import android.annotation.SuppressLint
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
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.taskify.database.Task
import com.example.taskify.database.UserRepository
import com.example.taskify.ui.theme.components.Tags
import java.util.*

class TaskAlarm : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {
        val title = intent.getStringExtra("NOTIFICATION_TITLE")
        val description = intent.getStringExtra("NOTIFICATION_CONTENT")
        try {
            if (description != null) {
                if (title != null) {
                    showNotification(
                        context, title, description
                    )
                }
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
private fun showNotification(context: Context, title:String, desc:String) {
    val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "message_channel"
    val channelName = "message_name"
    val builder = Notification.Builder(context)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, channelName, importance)
        manager.createNotificationChannel(channel)
        val builder = Notification.Builder(context, channelId)
        builder.setContentTitle(title)
            .setContentText(desc)
            .setSmallIcon(R.drawable.emailinbox)
            .setAutoCancel(true)
    }
    else{
        builder.setContentTitle(title)
            .setContentText(desc)
            .setSmallIcon(R.drawable.emailinbox)
            .setAutoCancel(true)
    }


    val notificationId = System.currentTimeMillis().toInt()
    manager.notify(notificationId, builder.build())
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
        true
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
fun createTask(navController: NavController, userRepository: UserRepository, userId: Long) {
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
//    val calendar = Calendar.getInstance()

    var date  by remember {
        mutableStateOf("")
    }
    var startTime by remember { mutableStateOf("") }
    var endTime by remember { mutableStateOf("") }


    val calendarForStartTime = Calendar.getInstance()
    val calendarForEndTime = Calendar.getInstance()
//    val mYear: Int
//    val mMonth: Int
//    val mDay: Int
//
//    val calendarInstance = Calendar.getInstance()
//    mYear = calendarInstance.get(Calendar.YEAR)
//    mMonth = calendarInstance.get(Calendar.MONTH)
//    mDay = calendarInstance.get(Calendar.DAY_OF_MONTH)
//    calendarInstance.time = Date()
//    val date = remember { mutableStateOf("") }

//    val mDatePickerDialog = DatePickerDialog(
//        context,
//        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
//            date.value = "$dayOfMonth/${month + 1}/$year"
//        }, mYear, mMonth, mDay
//    )


//    val startHour = calendarInstance[Calendar.HOUR_OF_DAY]
//    val startMinute = calendarInstance[Calendar.MINUTE]


//    val mTimePickerDialog = TimePickerDialog(
//        context,
//        { _, startHour: Int, startMinute: Int ->
//            startTime.value = "$startHour:$startMinute"
//        }, startHour, startMinute, false
//    )

//    val endHour = calendarInstance[Calendar.HOUR_OF_DAY]
//    val endMinute = calendarInstance[Calendar.MINUTE]
//    val endTimePickerDialog = TimePickerDialog(
//        context,
//        { _, endHour: Int, endMinute: Int ->
//            endTime.value = "$endHour:$endMinute"
//        }, endHour, endMinute, false
//    )
    Scaffold(modifier = Modifier.padding(
        top = 10.dp,
        bottom = 10.dp
    ),
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(108.dp),
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
                                    navController.navigateUp()
                                })
                        )
                        Text(
                            text = "Create task",
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
                placeholder = {
                    Text(
                        text = "Title",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
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
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.calendar),
                    contentDescription = "",
                    tint = Color.Blue,
                    modifier = Modifier
                        .size(20.dp)
                        .padding(start = 5.dp)
                )

                ClickableText(text = buildAnnotatedString {
                    Text(
                        text = "Date",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
                        )
                    )
                },
                    onClick = {
                        showDatePicker(context, calendarForStartTime) {
                            date  = formatDate(calendarForStartTime)
//                                "${calendar.get(Calendar.DAY_OF_MONTH)}/${calendar.get(Calendar.MONTH) + 1}/${calendar.get(Calendar.YEAR)}"
                        }
                    })
                Text(
                    text = date.ifEmpty { "Set Date" },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF6368D9),
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.28.sp,
                    )
                )

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp)
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
//                                        "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
                                }
                            }
                        )
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 10.dp)
                    )

                    Text(
                        text = "Start Time:",
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
                        .border(
                            border = BorderStroke(1.dp, Color.Gray),
                            shape = RoundedCornerShape(2.dp)
                        )
                        .clickable(onClick = {
                            showTimePicker(context, calendarForEndTime) {
                                endTime = formatTime(calendarForEndTime)
//                                    "${calendar.get(Calendar.HOUR_OF_DAY)}:${calendar.get(Calendar.MINUTE)}"
                            }
                        })
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 10.dp)
                    )
                    Text(
                        text = "End Time:",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight(500),
                            color = Color(0xFF6368D9),
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.28.sp,
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
                    )

                }

            }
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            )
            {
                item {

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
                }
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    )
                    {
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
                        Tags(label = "Study",
                            modifier = Modifier
                                .width(115.dp)
                                .clickable { selectedCategory = "Study" },
                            selected = selectedCategory == "Study",
                            onTagClicked = { selectedCategory = "Study" }
                        )
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

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                maxLines = 200,
                placeholder = {
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
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = Color(0xFFD1D0F9),
                            shape = RoundedCornerShape(20.dp)
                        )
                        .height(35.dp)
                        .width(30.dp)
                )
                {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            Icons.Default.Delete,
                            contentDescription = "delete",
                            tint = Color(0xFF6368D9),
                            modifier = Modifier.size(24.dp)

                        )
                    }

                }
                Spacer(modifier = Modifier.width(10.dp))
                ElevatedButton(
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF6368D9)
                    ),

                    onClick =
                    {
                        if(validateDateTime(calendarForStartTime, calendarForEndTime)){
                            if (selectedCategory.isNotBlank()) {
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
                                scheduleNotification(context, title, "Task starts now: $description", calendarForStartTime,1)
                                scheduleNotification(context, title, "Task ends now: $description", calendarForEndTime,2)

                                navController.popBackStack()

                            }
                            else
                            {
                                Toast.makeText(context, "Please select a category", Toast.LENGTH_SHORT).show()
                            }

                        }
                        else{
                            Toast.makeText(context, "Start time must be before end time", Toast.LENGTH_SHORT).show()
                        }
                    }
                ){
                    Text(text = "Save task")
                }



            }
        }

    }

}
private fun formatDate(calendar: Calendar): String {
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val month = calendar.get(Calendar.MONTH) + 1
    val year = calendar.get(Calendar.YEAR)
    return "$day/$month/$year"
}

private fun formatTime(calendar: Calendar): String {
    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)
    return "$hour:$minute"
}

private fun validateDateTime(startCalendar: Calendar, endCalendar: Calendar): Boolean {
    return startCalendar.before(endCalendar)
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