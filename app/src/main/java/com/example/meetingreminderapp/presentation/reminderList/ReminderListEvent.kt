package com.example.meetingreminderapp.presentation.reminderList

import com.example.meetingreminderapp.domain.models.ReminderItem

sealed class ReminderListEvent {
    data class DeleteReminderItem(val reminderItem: ReminderItem): ReminderListEvent()
    object RestoreReminderItem: ReminderListEvent()
}