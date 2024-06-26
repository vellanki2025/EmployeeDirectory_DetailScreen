package com.organization.directory.core.di

import android.content.Context
import androidx.room.Room
import com.organization.directory.core.util.Constants
import com.organization.directory.data.local.EmployeeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideEmployeeDatabase(@ApplicationContext context: Context): EmployeeDatabase {
        return Room.databaseBuilder(
            context,
            EmployeeDatabase::class.java,
            Constants.EMPLOYEE_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}