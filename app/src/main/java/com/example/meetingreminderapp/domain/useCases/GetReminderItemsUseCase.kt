package com.example.meetingreminderapp.domain.useCases

import com.example.meetingreminderapp.domain.models.ReminderItem
import com.example.meetingreminderapp.domain.repository.ReminderItemsRepository
import kotlinx.coroutines.flow.Flow

class GetReminderItemsUseCase (
    private val remindersRepository: ReminderItemsRepository
) {

    operator fun invoke(): Flow<List<ReminderItem>> {
        return remindersRepository.getReminderItems()
    }
}