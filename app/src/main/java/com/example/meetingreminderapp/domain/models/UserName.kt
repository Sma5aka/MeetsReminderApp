package com.example.meetingreminderapp.domain.models

import java.io.Serializable

data class UserName(
    val first: String,
    val last: String,
    val title: String
): Serializable
