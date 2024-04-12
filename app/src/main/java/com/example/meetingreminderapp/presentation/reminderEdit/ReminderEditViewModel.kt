package com.example.meetingreminderapp.presentation.reminderEdit

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.meetingreminderapp.domain.models.InvalidReminderItemException
import com.example.meetingreminderapp.domain.models.ReminderItem
import com.example.meetingreminderapp.domain.models.UserData
import com.example.meetingreminderapp.domain.models.UserName
import com.example.meetingreminderapp.domain.models.UserPicture
import com.example.meetingreminderapp.domain.useCases.ReminderItemUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReminderEditViewModel @Inject constructor(
    private val reminderItemUseCases: ReminderItemUseCases,
    savedStateHandle: SavedStateHandle,
    val sharedViewModel: SharedViewModel
): ViewModel(){

    private val _itemTitle = mutableStateOf("")
    val itemTitle: State<String> = _itemTitle

    private val _itemDate = mutableStateOf("")
    val itemDate: State<String> = _itemDate

    private val _itemTime = mutableStateOf("")
    val itemTime: State<String> = _itemTime

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentItemId: Int? = null

    init {
        savedStateHandle.get<Int>("noteId")?.let { noteId ->
            if (noteId != -1) {
                viewModelScope.launch {
                    reminderItemUseCases.getReminderItemByIdUseCase(noteId)?.also {
                        currentItemId = it.id
                        _itemTitle.value = it.name
                        _itemDate.value = it.date
                        _itemTime.value = it.time
                        sharedViewModel.onUserSelected(UserData(
                            email = it.userEmail,
                            name = UserName(it.userNameFirst, it.userNameLast, it.userNameTitle),
                            picture = UserPicture(it.userPictureLarge, it.userPictureMedium, it.userPictureThumbnail)
                        ))

                    }
                }
            }
        }

    }

    fun onEvent(event: ReminderEditEvent) {
        when(event) {
            is ReminderEditEvent.EnteredTitle -> {
                _itemTitle.value = event.value
            }
            is ReminderEditEvent.PickedDate -> {
                _itemDate.value = event.value
            }
            is ReminderEditEvent.PickedTime -> {
                _itemTime.value = event.value
            }
            is ReminderEditEvent.SaveRemindItem -> {
                viewModelScope.launch {
                    try {
                        reminderItemUseCases.insertReminderItemUseCase(
                            ReminderItem(
                                name = itemTitle.value,
                                date = itemDate.value,
                                time = itemTime.value,
                                //Если приходит UserData, то все нижеуказанные поля гарантированно существуют
                                userEmail = sharedViewModel.selectedUser.value!!.email,
                                userNameLast = sharedViewModel.selectedUser.value!!.name.last,
                                userNameFirst = sharedViewModel.selectedUser.value!!.name.first,
                                userNameTitle = sharedViewModel.selectedUser.value!!.name.title,
                                userPictureLarge = sharedViewModel.selectedUser.value!!.picture.large,
                                userPictureMedium = sharedViewModel.selectedUser.value!!.picture.medium,
                                userPictureThumbnail = sharedViewModel.selectedUser.value!!.picture.thumbnail,
                                id = currentItemId
                            )
                        )
                        _eventFlow.emit(UiEvent.SaveRemind)
                    } catch (e: InvalidReminderItemException) {
                        _eventFlow.emit(
                            UiEvent.ShowSnackbar(
                                message = e.message ?: "Невозможно сохранить"
                            )
                        )
                    }
                }
            }
            is ReminderEditEvent.goBack -> {
                event.navController.popBackStack()
            }
        }
    }

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveRemind: UiEvent()
    }
}