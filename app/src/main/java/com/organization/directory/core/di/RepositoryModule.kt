package com.organization.directory.core.di

import com.organization.directory.data.api.EmployeeApi
import com.organization.directory.data.local.EmployeeDatabase
import com.organization.directory.data.mapper.EmployeeDtoMapper
import com.organization.directory.data.repository.EmployeeRepositoryImpl
import com.organization.directory.domain.repository.EmployeeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Dagger module for providing the implementation of [EmployeeRepository].
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    /**
     * Provides a singleton instance of [EmployeeRepository] using the provided data and mapper.
     *
     * @param employeeApi The implementation of [EmployeeApi].
     * @param dtoMapper The implementation of [EmployeeDtoMapper].
     * @return An instance of [EmployeeRepository] created using the provided data and mapper.
     */
    @Singleton
    @Provides
    fun provideRepo(
        employeeApi: EmployeeApi,
        dtoMapper: EmployeeDtoMapper,
        database: EmployeeDatabase
    ): EmployeeRepository {
        return EmployeeRepositoryImpl(employeeApi, dtoMapper, database.employeeDao)
    }
}