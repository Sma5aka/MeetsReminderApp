package com.example.meetingreminderapp.presentation.reminderList

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.meetingreminderapp.presentation.navigation.Screen
import com.example.meetingreminderapp.presentation.reminderEditClients.ReminderEditClientsViewModel
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ReminderListScreen(
    navController: NavController,
    viewModel: ReminderListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.ReminderEditScreen.route)
                },
                ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add from snackbar"
                )
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = {
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
            ) {
                items(state.reminderItems) { item ->
                    ReminderListItem(
                        reminderItem = item,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .clickable {
                                       navController.navigate(
                                           Screen.ReminderEditScreen.route +
                                           "?noteId=${item.id}"
                                       )
                                       },
                        onDeleteClick = {
                            viewModel.onEvent(ReminderListEvent.DeleteReminderItem(item))
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    message = "Напоминание удалено",
                                    actionLabel = "Отменить"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(ReminderListEvent.RestoreReminderItem)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    )
}