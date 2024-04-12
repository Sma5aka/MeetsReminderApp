package com.example.meetingreminderapp.domain.repository

import com.example.meetingreminderapp.domain.models.ReminderItem
import kotlinx.coroutines.flow.Flow

interface ReminderItemsRepository {

    fun getReminderItems(): Flow<List<ReminderItem>>

    suspend fun getReminderItemById(id: Int): ReminderItem?

    suspend fun insertReminderItem(reminderItem: ReminderItem)

    suspend fun deleteReminderItem(reminderItem: ReminderItem)

}