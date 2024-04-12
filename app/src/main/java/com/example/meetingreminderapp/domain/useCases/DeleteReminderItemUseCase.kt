package com.example.meetingreminderapp.domain.useCases

import com.example.meetingreminderapp.domain.models.ReminderItem
import com.example.meetingreminderapp.domain.repository.ReminderItemsRepository

class DeleteReminderItemUseCase (
    private val remindersRepository: ReminderItemsRepository
) {
    suspend operator fun invoke(reminderItem: ReminderItem) {
        remindersRepository.deleteReminderItem(reminderItem)
    }
}