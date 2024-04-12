package com.example.meetingreminderapp.data.dataSource.remoteApi.dto

import com.example.meetingreminderapp.domain.models.UserPicture

data class Picture(
    val large: String,
    val medium: String,
    val thumbnail: String
)

fun Picture.toUserPicture(): UserPicture {
    return UserPicture(large, medium, thumbnail)
}