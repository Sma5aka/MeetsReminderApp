package com.example.meetingreminderapp.domain.repository

import com.example.meetingreminderapp.domain.models.UserData

interface UsersRepository {
    suspend fun getUsers(numOfResults: Int): List<UserData>
}