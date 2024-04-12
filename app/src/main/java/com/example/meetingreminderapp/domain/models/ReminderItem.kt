package com.example.meetingreminderapp.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ReminderItem (
    val name: String,
    val date: String,
    val time: String,
    val userEmail: String,
    val userNameFirst: String,
    val userNameLast: String,
    val userNameTitle: String,
    val userPictureLarge: String,
    val userPictureMedium: String,
    val userPictureThumbnail: String,
    @PrimaryKey val id: Int? = null
)

class InvalidReminderItemException(message: String): Exception(message)