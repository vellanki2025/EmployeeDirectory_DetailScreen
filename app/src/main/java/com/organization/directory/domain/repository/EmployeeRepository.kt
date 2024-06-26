package com.organization.directory.domain.repository

import com.organization.directory.core.common.Outcome
import com.organization.directory.domain.model.Employee

/**
 * Interface representing fetching employee data which are fetched from the backend.
 */
interface EmployeeRepository {

    /**
     * Fetches the list of employees from the backend.
     *
     * @return A [Outcome] object representing the result of the operation.
     *         It encapsulates the data as a List of [Employee] objects if successful.
     *         If an error occurs, the [Outcome] will hold an empty list.
     */
    suspend fun getEmployeeData(): Outcome<List<Employee>>

    /**
     * Fetches the list of employees with missing data from the backend.
     *
     * @return A [Outcome] object representing the result of the operation.
     *         It encapsulates the data as a List of [Employee] objects if successful.
     *         If an error occurs, the [Outcome] will hold empty list.
     */
    suspend fun getMalfunctionedEmployeeData(): Outcome<List<Employee>>

    /**
     * Fetches the empty list of employees from the backend.
     *
     * @return A [Outcome] object representing the result of the operation.
     *         It encapsulates the data as a List of [Employee] objects if successful.
     *         If an error occurs, the [Outcome] will hold an empty list.
     */
    suspend fun getEmptyEmployeeData(): Outcome<List<Employee>>

    /**
     * Get single employee details
     */
    suspend fun getEmployeeDetails(id: String):  Outcome<Employee?>

}