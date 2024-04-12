package com.example.meetingreminderapp.presentation.reminderEdit

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meetingreminderapp.presentation.navigation.Screen
import com.example.meetingreminderapp.presentation.reminderList.LoadingImage
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReminderEditScreen(
    navController: NavController,
    viewModel: ReminderEditViewModel = hiltViewModel()
){

    val snackbarHostState = remember { SnackbarHostState() }

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)

    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val time = remember { mutableStateOf("") }

    val timePickerDialog = TimePickerDialog(
        context,
        {_, hour : Int, minute: Int ->
            time.value = "$hour:$minute"
            viewModel.onEvent(ReminderEditEvent.PickedTime(time.value))
        }, hour, minute, false
    )

    val datePickerDialog = DatePickerDialog(
        context,
        {_: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "${dayOfMonth}/${month}/${year}"
            viewModel.onEvent(ReminderEditEvent.PickedDate(date.value))
        }, year, month, day
    )

    val currentDate = System.currentTimeMillis()
    calendar.add(Calendar.YEAR, 1)
    val maxDate = calendar.timeInMillis

    datePickerDialog.datePicker.minDate = currentDate
    datePickerDialog.datePicker.maxDate = maxDate


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier
            .fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                viewModel.onEvent(ReminderEditEvent.SaveRemindItem)
                viewModel.onEvent(ReminderEditEvent.goBack(navController))
            }) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Save"
                )
            }
        },
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {


                        Column {

                            IconButton(
                                onClick = {navController.popBackStack()},
                                modifier = Modifier.size(50.dp),
                                colors = IconButtonColors(
                                    MaterialTheme.colorScheme.primaryContainer,
                                    MaterialTheme.colorScheme.primary,
                                    MaterialTheme.colorScheme.primaryContainer,
                                    MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Go back",
                                    tint = Color.Black
                                )
                            }
                            Spacer(modifier = Modifier.size(10.dp))

                            Box {
                                BasicTextField(
                                    textStyle = TextStyle(
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    ),
                                    value = viewModel.itemTitle.value,
                                    onValueChange = {
                                        viewModel.onEvent(ReminderEditEvent.EnteredTitle(it))
                                    },
                                    modifier = Modifier.fillMaxWidth(),
                                    maxLines = 3

                                )
                                if (viewModel.itemTitle.value.isEmpty()) {
                                    Text(
                                        text = "Название",
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.Gray
                                    )
                                }

                            }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .border(2.dp, Color.LightGray)
                    ) {
                        if (viewModel.sharedViewModel.selectedUser.value != null) {
                            viewModel.sharedViewModel.selectedUser.value?.let { it1 ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp)
                                        .clickable {
                                            navController.navigate(Screen.ReminderEditClientsScreen.route)
                                        },
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(90.dp)
                                    ) {
                                        LoadingImage(imgUrl = viewModel.sharedViewModel.selectedUser.value!!.picture.large)
                                    }

                                    Column (
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalArrangement = Arrangement.SpaceEvenly,
                                        horizontalAlignment = Alignment.End
                                    ){
                                        Text(
                                            text = "${viewModel.sharedViewModel.selectedUser.value!!.name.last} " +
                                                    "${viewModel.sharedViewModel.selectedUser.value!!.name.first} " +
                                                    viewModel.sharedViewModel.selectedUser.value!!.name.title,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                        Text(
                                            text = viewModel.sharedViewModel.selectedUser.value!!.email,
                                            style = MaterialTheme.typography.bodyLarge
                                        )
                                    }
                                }
                            }
                        } else {
                            Text(
                                text = "Выберите клиента",
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Gray,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .clickable {
                                        navController.navigate(Screen.ReminderEditClientsScreen.route)
                                    },
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "Выбранная дата:",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.size(16.dp))

                        Text(
                            text = viewModel.itemDate.value,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.size(16.dp))

                        Button(onClick = {
                            datePickerDialog.show()
                        }) {
                            Text(text = "Выбрать дату")
                        }

                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = "Выбранное время:",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            text = viewModel.itemTime.value,
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Spacer(modifier = Modifier.size(16.dp))

                        Button(onClick = {
                            timePickerDialog.show()
                        }) {
                            Text(text = "Выбрать время")
                        }

                    }
                }
            }
        }
    )
}