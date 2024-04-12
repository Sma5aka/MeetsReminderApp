package com.example.meetingreminderapp.presentation.reminderList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meetingreminderapp.domain.models.ReminderItem
import com.example.meetingreminderapp.domain.useCases.ReminderItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderListViewModel @Inject constructor(
    private val reminderItemUseCases: ReminderItemUseCases
): ViewModel() {

    private val _state = mutableStateOf(ReminderListState())
    val state: State<ReminderListState> = _state

    private var deletedItem: ReminderItem? = null

    private var getRemindersJob: Job? = null

    init {
        getReminderItems()
    }

    fun onEvent(event: ReminderListEvent) {
        when(event){
            is ReminderListEvent.DeleteReminderItem -> {

                viewModelScope.launch {
                    reminderItemUseCases.deleteReminderItemUseCase(event.reminderItem)
                    deletedItem = event.reminderItem
                }
            }
            is ReminderListEvent.RestoreReminderItem -> {
                viewModelScope.launch {
                    reminderItemUseCases.insertReminderItemUseCase(deletedItem ?: return@launch)
                    deletedItem = null
                }
            }

        }
    }

    private fun getReminderItems() {
        getRemindersJob?.cancel()
        getRemindersJob = reminderItemUseCases.getReminderItemsUseCase()
            .onEach { reminders ->
                _state.value = state.value.copy(reminders)
            }.launchIn(viewModelScope)
    }

}