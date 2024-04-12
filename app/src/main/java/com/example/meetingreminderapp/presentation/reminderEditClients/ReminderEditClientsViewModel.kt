package com.example.meetingreminderapp.presentation.reminderEditClients

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetingreminderapp.domain.useCases.GetUsersUseCase
import com.example.meetingreminderapp.domain.util.Resource
import com.example.meetingreminderapp.presentation.reminderEdit.SharedViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReminderEditClientsViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    val sharedViewModel: SharedViewModel
): ViewModel() {

    private val _state = mutableStateOf(ReminderEditClientsState())
    val state: State<ReminderEditClientsState> = _state

    init {
        getUsers(numOfResults = 10)
    }

    private fun getUsers(numOfResults: Int) {
        getUsersUseCase(numOfResults).onEach {result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = ReminderEditClientsState(users = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ReminderEditClientsState(error = result.message ?: "Unknown error")
                }
                is Resource.Loading -> {
                    _state.value = ReminderEditClientsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}