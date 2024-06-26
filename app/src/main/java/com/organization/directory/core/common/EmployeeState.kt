package com.organization.directory.core.common

import com.organization.directory.domain.model.Employee

/**
 * Sealed interface representing the various states of the Employee data,
 * which can be Success, Failure, Empty, and Loading states.
 */
sealed interface EmployeeState {

    /**
     * Represents the Success state of the Employee data with the list of employees.
     *
     * @param data The list of [Employee] objects representing the employees if the operation is successful.
     */
    data class Success(val data: List<Employee>): EmployeeState

    /**
     * Represents the Failure state of the Employee data with an error message.
     *
     * @param errorMessage The error message indicating the cause of the failure if an error occurs.
     */
    data class Failure(val errorMessage: String?): EmployeeState

    /**
     * Represents the Empty state of the Employee data when there are no employees.
     */
    data class Empty(val noResultsMessage: String?): EmployeeState

    /**
     * Represents the Loading state of the Employee data when data is being fetched.
     */
    data object Loading: EmployeeState
}