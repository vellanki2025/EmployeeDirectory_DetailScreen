package com.organization.directory.data.api

import com.organization.directory.data.entity.EmployeeListDto
import com.organization.directory.utils.NetworkConstants
import retrofit2.http.GET

/**
 * Retrofit API interface for retrieving Employee data from the backend.
 * This interface provides methods to interact with the Employee API endpoints.
 */
interface EmployeeApi {

    /**
     * Retrieves the list of all employees from the backend.
     *
     * @return [EmployeeListDto] object representing the list of employees received from the backend.
     */
    @GET(NetworkConstants.EMPLOYEE_END_POINT)
    suspend fun getEmployeeData(): EmployeeListDto

    /**
     * Retrieves the list of all employees from the backend with some missing employee data
     *
     * @return [EmployeeListDto] object representing the list of employees received from the backend.
     */
    @GET(NetworkConstants.MALFUNCTIONED_EMPLOYEE_END_POINT)
    suspend fun getMalfunctionedEmployeeData(): EmployeeListDto

    /**
     * Retrieves an empty list of employees from the backend.
     *
     * @return [EmployeeListDto] object representing empty list of employees.
     */
    @GET(NetworkConstants.EMPTY_EMPLOYEE_END_POINT)
    suspend fun getEmptyEmployeeData(): EmployeeListDto
}