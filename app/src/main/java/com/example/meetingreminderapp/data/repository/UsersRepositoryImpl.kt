package com.example.meetingreminderapp.data.repository

import com.example.meetingreminderapp.data.dataSource.remoteApi.RandomuserApi
import com.example.meetingreminderapp.data.dataSource.remoteApi.dto.toUserData
import com.example.meetingreminderapp.domain.models.UserData
import com.example.meetingreminderapp.domain.repository.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val apiService: RandomuserApi
): UsersRepository {
    override suspend fun getUsers(numOfResults: Int): List<UserData> =
        apiService.getUsers(numOfResults).results.map { it.toUserData() }
}