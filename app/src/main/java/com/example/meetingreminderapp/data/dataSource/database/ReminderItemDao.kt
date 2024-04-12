package com.example.meetingreminderapp.data.dataSource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.meetingreminderapp.domain.models.ReminderItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ReminderItemDao {

    @Query("SELECT * FROM reminderitem")
    fun getReminderItem(): Flow<List<ReminderItem>>

    @Query("SELECT * FROM reminderitem WHERE id = :id")
    suspend fun getReminderItemById(id: Int): ReminderItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminderItem(reminderItem: ReminderItem)

    @Delete
    suspend fun deleteReminderItem(reminderItem: ReminderItem)
}