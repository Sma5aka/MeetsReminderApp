package com.example.meetingreminderapp.presentation.navigation

sealed class Screen(val route: String) {
    object ReminderListScreen: Screen("reminder_list")
    object ReminderEditScreen: Screen("reminder_edit")
    object ReminderEditClientsScreen: Screen("reminder_edit_clients")
}