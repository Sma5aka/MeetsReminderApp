package com.example.meetingreminderapp.data.dataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.meetingreminderapp.domain.models.ReminderItem

@Database(
    entities = [ReminderItem::class],
    version = 1
)
abstract class RemindersDatabase: RoomDatabase() {
    abstract val reminderItemDao: ReminderItemDao

    companion object {
        const val DATABASE_NAME = "reminders_db"
    }
}