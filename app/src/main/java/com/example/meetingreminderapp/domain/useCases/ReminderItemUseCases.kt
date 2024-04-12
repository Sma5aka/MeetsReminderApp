package com.example.meetingreminderapp.domain.useCases

data class ReminderItemUseCases (
    val getReminderItemsUseCase: GetReminderItemsUseCase,
    val deleteReminderItemUseCase: DeleteReminderItemUseCase,
    val insertReminderItemUseCase: InsertReminderItemUseCase,
    val getReminderItemByIdUseCase: GetReminderItemByIdUseCase
)