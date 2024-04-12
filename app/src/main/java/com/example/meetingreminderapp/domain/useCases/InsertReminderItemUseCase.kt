package com.example.meetingreminderapp.domain.useCases

import com.example.meetingreminderapp.domain.models.InvalidReminderItemException
import com.example.meetingreminderapp.domain.models.ReminderItem
import com.example.meetingreminderapp.domain.repository.ReminderItemsRepository
import kotlin.jvm.Throws

class InsertReminderItemUseCase (
    private val remindersRepository: ReminderItemsRepository
) {
    @Throws(InvalidReminderItemException::class)
    suspend operator fun invoke(reminderItem: ReminderItem) {
        if (reminderItem.name.isBlank()) {
            throw InvalidReminderItemException("Введите название напоминания")
        }
        if (reminderItem.date.isBlank()) {
            throw InvalidReminderItemException("Выберите дату встречи")
        }
        remindersRepository.insertReminderItem(reminderItem)
    }
}