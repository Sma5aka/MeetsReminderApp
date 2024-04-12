package com.example.meetingreminderapp.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.meetingreminderapp.presentation.navigation.Screen
import com.example.meetingreminderapp.presentation.reminderEdit.ReminderEditScreen
import com.example.meetingreminderapp.presentation.reminderEditClients.ReminderEditClientsScreen
import com.example.meetingreminderapp.presentation.reminderList.ReminderListScreen
import com.example.meetingreminderapp.presentation.theme.MeetingReminderAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MeetingReminderAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ReminderListScreen.route
                    ) {
                        composable(
                            route = Screen.ReminderEditClientsScreen.route
                        ) {
                            ReminderEditClientsScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ReminderListScreen.route
                        ) {
                            ReminderListScreen(navController = navController)
                        }
                        composable(
                            route = Screen.ReminderEditScreen.route +
                            "?noteId={noteId}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) {
                            ReminderEditScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}