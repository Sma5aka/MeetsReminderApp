package com.example.meetingreminderapp.presentation.reminderList

import com.example.meetingreminderapp.domain.models.ReminderItem

data class ReminderListState (
    val reminderItems: List<ReminderItem> = emptyList()
)