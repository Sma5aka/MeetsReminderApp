package com.example.meetingreminderapp.domain.useCases

import com.example.meetingreminderapp.domain.models.UserData
import com.example.meetingreminderapp.domain.repository.UsersRepository
import com.example.meetingreminderapp.domain.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    operator fun invoke(numOfResults: Int): Flow<Resource<List<UserData>>> = flow {
        try {
            emit(Resource.Loading())
            val users = repository.getUsers(numOfResults)
            emit(Resource.Success(users))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Unexpected error"))

        } catch (e: IOException) {
            emit(Resource.Error("No internet"))
        }
    }
}