package com.example.meetingreminderapp.data.repository

import com.example.meetingreminderapp.data.dataSource.database.ReminderItemDao
import com.example.meetingreminderapp.domain.models.ReminderItem
import com.example.meetingreminderapp.domain.repository.ReminderItemsRepository
import kotlinx.coroutines.flow.Flow

class ReminderItemsRepositoryImpl(
    private val dao: ReminderItemDao
): ReminderItemsRepository {
    override fun getReminderItems(): Flow<List<ReminderItem>> {
        return dao.getReminderItem()
    }

    override suspend fun getReminderItemById(id: Int): ReminderItem? {
        return dao.getReminderItemById(id)
    }

    override suspend fun insertReminderItem(reminderItem: ReminderItem) {
        return dao.insertReminderItem(reminderItem)
    }

    override suspend fun deleteReminderItem(reminderItem: ReminderItem) {
        return dao.deleteReminderItem(reminderItem)
    }
}