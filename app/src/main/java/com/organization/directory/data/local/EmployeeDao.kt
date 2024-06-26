package com.organization.directory.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.organization.directory.core.util.Constants
import com.organization.directory.data.entity.EmployeeDto

@Dao
interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllEmployees(employees: List<EmployeeDto>)

    @Query("SELECT * FROM ${Constants.EMPLOYEE_TABLE_NAME}")
    suspend fun getAllEmployees(): List<EmployeeDto>

    @Query("SELECT * FROM ${Constants.EMPLOYEE_TABLE_NAME} WHERE uuid LIKE :employeeId")
    suspend fun getEmployeeDetails(employeeId: String): EmployeeDto

}