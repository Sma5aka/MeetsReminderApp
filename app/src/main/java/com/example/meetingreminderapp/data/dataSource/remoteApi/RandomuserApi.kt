package com.example.meetingreminderapp.data.dataSource.remoteApi

import com.example.meetingreminderapp.data.dataSource.remoteApi.dto.Results
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomuserApi {
    @GET("/api")
    suspend fun getUsers(
        @Query("results") numOfResults: Int
    ): Results
}