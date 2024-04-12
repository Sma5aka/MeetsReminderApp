package com.example.meetingreminderapp.data.dataSource.remoteApi.dto

import com.example.meetingreminderapp.domain.models.UserData

data class UserDto(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)

fun UserDto.toUserData(): UserData {
    return UserData(
        email = email,
        name = name.toUserName(),
        picture = picture.toUserPicture()
    )
}