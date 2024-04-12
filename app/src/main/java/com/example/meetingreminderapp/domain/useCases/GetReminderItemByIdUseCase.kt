package com.example.meetingreminderapp.domain.useCases

import com.example.meetingreminderapp.domain.models.ReminderItem
import com.example.meetingreminderapp.domain.repository.ReminderItemsRepository

class GetReminderItemByIdUseCase (
    private val remindersRepository: ReminderItemsRepository
) {
    suspend operator fun invoke(id: Int): ReminderItem? {
        return remindersRepository.getReminderItemById(id)
    }
}