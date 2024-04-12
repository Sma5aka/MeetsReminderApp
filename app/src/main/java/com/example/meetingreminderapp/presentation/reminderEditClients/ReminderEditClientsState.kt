package com.example.meetingreminderapp.presentation.reminderEditClients

import com.example.meetingreminderapp.domain.models.UserData

data class ReminderEditClientsState(
    val isLoading: Boolean = false,
    val users: List<UserData> = emptyList(),
    val error: String = ""
)