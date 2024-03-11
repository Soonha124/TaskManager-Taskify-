package com.example.taskify

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.widget.DatePicker
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
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import com.example.taskify.ui.theme.components.Tags
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createTask(navController: NavController) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }
    val mContext = LocalContext.current
    val mYear: Int
    val mMonth: Int
    val mDay: Int

    val mCalendar = Calendar.getInstance()
    mYear = mCalendar.get(Calendar.YEAR)
    mMonth = mCalendar.get(Calendar.MONTH)
    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
    mCalendar.time = Date()
    val mDate = remember { mutableStateOf("") }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mDate.value = "$mDayOfMonth/${mMonth + 1}/$mYear"
        }, mYear, mMonth, mDay
    )


    val startHour = mCalendar[Calendar.HOUR_OF_DAY]
    val startMinute = mCalendar[Calendar.MINUTE]

    val startTime = remember { mutableStateOf("") }

    val mTimePickerDialog = TimePickerDialog(
        mContext,
        { _, startHour: Int, startMinute: Int ->
            startTime.value = "$startHour:$startMinute"
        }, startHour, startMinute, false
    )
    val endTime = remember { mutableStateOf("") }

    val endHour = mCalendar[Calendar.HOUR_OF_DAY]
    val endMinute = mCalendar[Calendar.MINUTE]
    val endTimePickerDialog = TimePickerDialog(
        mContext,
        { _, endHour: Int, endMinute: Int ->
            endTime.value = "$endHour:$endMinute"
        }, endHour, endMinute, false
    )
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
    { innerPading ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier
                .padding(innerPading)
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
                    onClick = { mDatePickerDialog.show() })
                Text(
                    text = "${mDate.value}",
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
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 10.dp)
                    )
                    ClickableText(text = buildAnnotatedString {
                        Text(
                            text = "Start Time:",
                            style = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF6368D9),
                                textAlign = TextAlign.Center,
                                letterSpacing = 0.28.sp,
                            )
                        )
                    },
                        onClick = { mTimePickerDialog.show() })
                    Text(
                        text = " ${startTime.value}",
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
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.clock),
                        contentDescription = "",
                        tint = Color.Blue,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(start = 10.dp)
                    )
                    ClickableText(text = buildAnnotatedString {
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
                    },
                        onClick = { endTimePickerDialog.show() })
                    Text(
                        text = " ${endTime.value}",
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
                        Tags(label = "Work",
                            modifier = Modifier.width(115.dp))
                        Tags(label = "Personal",
                            modifier = Modifier.width(115.dp))
                        Tags(label = "Other",
                            modifier = Modifier.width(115.dp))
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
                ElevatedButton(shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                        containerColor = Color(0xFF6368D9)
                    ),

                    onClick = { /*TODO*/ })
                {
                    Text(text = "Save task")
                }
            }
        }

    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun previewCreateTask() {
    createTask(rememberNavController())
}