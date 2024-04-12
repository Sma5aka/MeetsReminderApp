package com.example.meetingreminderapp.domain.models

import java.io.Serializable

data class UserData(
    val email: String,
    val name: UserName,
    val picture: UserPicture
): Serializable
