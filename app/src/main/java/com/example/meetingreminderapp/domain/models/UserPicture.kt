package com.example.meetingreminderapp.domain.models

import java.io.Serializable

data class UserPicture(
    val large: String,
    val medium: String,
    val thumbnail: String
): Serializable
