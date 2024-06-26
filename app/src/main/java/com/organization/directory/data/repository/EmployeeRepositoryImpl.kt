package com.organization.directory.data.repository

import com.organization.directory.core.common.Outcome
import com.organization.directory.data.api.EmployeeApi
import com.organization.directory.data.local.EmployeeDao
import com.organization.directory.data.mapper.EmployeeDtoMapper
import com.organization.directory.domain.model.Employee
import com.organization.directory.domain.repository.EmployeeRepository
import javax.inject.Inject

/**
 * Implementation of [EmployeeRepository] interface to fetch Employee data from the backend.
 */
class EmployeeRepositoryImpl @Inject constructor(
    private val employeeApi: EmployeeApi,
    private val dtoMapper: EmployeeDtoMapper,
    private val employeeDao: EmployeeDao
) : EmployeeRepository {

    override suspend fun getEmployeeData(): Outcome<List<Employee>> {
        return try {
            // fetch list from db
            val results = employeeDao.getAllEmployees();

            if (results.isEmpty()) {
                getEmployeeListFromServer()
            } else {
                // mapping
                val data = dtoMapper.mapFromEntity(results)
                Outcome.Success(data)
            }
        } catch (e: Exception) {
            getEmployeeListFromServer()
        }
    }

    private suspend fun getEmployeeListFromServer(): Outcome<List<Employee>> {
        return try {
            val results = employeeApi.getEmployeeData()

            // save to database
            employeeDao.insertAllEmployees(results.employees)

            // mapping
            val data = dtoMapper.mapFromEntity(results.employees)

            Outcome.Success(data)
        } catch (e: Exception) {
            Outcome.Error(e.message)
        }
    }




    /*
    override suspend fun getEmployeeData(): Outcome<List<Employee>> {
        return try {
            val results = employeeApi.getEmployeeData()

            // save to database
            employeeDao.insertAllEmployees(results.employees)

            // mapping
            val data = dtoMapper.mapFromEntity(results.employees)

            Outcome.Success(data)
        } catch (e: Exception) {
            //Outcome.Error(e.message)
            getEmployeeListFromDb()
        }
    }
    */

    // Ask is get all employee data from DB
    // if no network
    // on first launch

    /*
    private suspend fun getEmployeeListFromDb(): Outcome<List<Employee>> {
        return try {
            // fetch list from db
            val results = employeeDao.getAllEmployees();

            // mapping
            val data = dtoMapper.mapFromEntity(results)

            Outcome.Success(data)

        } catch (exception: Exception) {
            Outcome.Error(exception.message)
        }
    }
     */


    override suspend fun getMalfunctionedEmployeeData(): Outcome<List<Employee>> {
        return try {
            val data = dtoMapper.mapFromEntity(employeeApi.getMalfunctionedEmployeeData().employees)
            Outcome.Success(data)
        } catch (e: Exception) {
            Outcome.Error(e.message)
        }
    }

    override suspend fun getEmptyEmployeeData(): Outcome<List<Employee>> {
        return try {
            val data = dtoMapper.mapFromEntity(employeeApi.getEmptyEmployeeData().employees)
            Outcome.Success(data)
        } catch (e: Exception) {
            Outcome.Error(e.message)
        }
    }

    override suspend fun getEmployeeDetails(id: String): Outcome<Employee?> {
        return try {
            // fetch list from db
            val results = employeeDao.getEmployeeDetails(employeeId = id)
            val data = dtoMapper.mapFromEntity(listOf(results))
            Outcome.Success(data[0])
        } catch (e: Exception) {
            Outcome.Error(e.message)
        }
    }
}