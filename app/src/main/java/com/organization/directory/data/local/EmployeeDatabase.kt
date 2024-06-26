package com.organization.directory.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.organization.directory.core.util.Constants
import com.organization.directory.data.entity.EmployeeDto

@Database(
    entities = [EmployeeDto::class],
    version = Constants.EMPLOYEE_TABLE_NAME_VERSION,
    exportSchema = false
)
abstract class EmployeeDatabase : RoomDatabase() {
    abstract val employeeDao: EmployeeDao
}