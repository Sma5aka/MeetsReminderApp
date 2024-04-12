package com.example.meetingreminderapp.presentation.reminderEdit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.meetingreminderapp.domain.models.UserData

class SharedViewModel: ViewModel() {
    private val _selectedUser = mutableStateOf<UserData?>(null)
    val selectedUser: State<UserData?> = _selectedUser

    fun onUserSelected(user: UserData) {
        _selectedUser.value = user
    }
}