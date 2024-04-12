package com.example.meetingreminderapp.presentation.reminderEdit

import androidx.navigation.NavController

sealed class ReminderEditEvent {
    data class EnteredTitle(val value: String): ReminderEditEvent()
    data class PickedDate(val value: String): ReminderEditEvent()
    data class PickedTime(val value: String): ReminderEditEvent()
    object SaveRemindItem: ReminderEditEvent()

    data class goBack(val navController: NavController): ReminderEditEvent()
}