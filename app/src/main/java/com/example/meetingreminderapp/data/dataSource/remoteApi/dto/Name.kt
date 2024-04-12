package com.example.meetingreminderapp.data.dataSource.remoteApi.dto

import com.example.meetingreminderapp.domain.models.UserName

data class Name(
    val first: String,
    val last: String,
    val title: String
)

fun Name.toUserName(): UserName {
    return UserName(first, last, title)
}