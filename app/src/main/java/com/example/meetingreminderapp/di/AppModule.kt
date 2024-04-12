package com.example.meetingreminderapp.di

import android.app.Application
import androidx.room.Room
import com.example.meetingreminderapp.data.dataSource.database.RemindersDatabase
import com.example.meetingreminderapp.data.dataSource.remoteApi.RandomuserApi
import com.example.meetingreminderapp.data.repository.ReminderItemsRepositoryImpl
import com.example.meetingreminderapp.data.repository.UsersRepositoryImpl
import com.example.meetingreminderapp.domain.Constants
import com.example.meetingreminderapp.domain.repository.ReminderItemsRepository
import com.example.meetingreminderapp.domain.repository.UsersRepository
import com.example.meetingreminderapp.domain.useCases.DeleteReminderItemUseCase
import com.example.meetingreminderapp.domain.useCases.GetReminderItemByIdUseCase
import com.example.meetingreminderapp.domain.useCases.GetReminderItemsUseCase
import com.example.meetingreminderapp.domain.useCases.GetUsersUseCase
import com.example.meetingreminderapp.domain.useCases.InsertReminderItemUseCase
import com.example.meetingreminderapp.domain.useCases.ReminderItemUseCases
import com.example.meetingreminderapp.presentation.reminderEdit.SharedViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiServices(): RandomuserApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RandomuserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiServices: RandomuserApi): UsersRepository {
        return UsersRepositoryImpl(apiServices)
    }

    @Provides
    @Singleton
    fun provideRemindersDatabase(app: Application): RemindersDatabase {
        return Room.databaseBuilder(
            app,
            RemindersDatabase::class.java,
            RemindersDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providesReminderItemsRepository(db: RemindersDatabase): ReminderItemsRepository {
        return ReminderItemsRepositoryImpl(db.reminderItemDao)
    }

    @Provides
    @Singleton
    fun providesReminderItemUseCases(reminderItemsRepository: ReminderItemsRepository): ReminderItemUseCases {
        return ReminderItemUseCases(
            getReminderItemsUseCase = GetReminderItemsUseCase(reminderItemsRepository),
            deleteReminderItemUseCase = DeleteReminderItemUseCase(reminderItemsRepository),
            insertReminderItemUseCase = InsertReminderItemUseCase(reminderItemsRepository),
            getReminderItemByIdUseCase = GetReminderItemByIdUseCase(reminderItemsRepository)
        )
    }

    @Provides
    @Singleton
    fun providesGetUsersUseCase(usersRepository: UsersRepository): GetUsersUseCase {
        return GetUsersUseCase(usersRepository)
    }

    @Provides
    @Singleton
    fun providesSharedViewModel(): SharedViewModel {
        return SharedViewModel()
    }

}