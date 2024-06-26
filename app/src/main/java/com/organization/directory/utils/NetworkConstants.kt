package com.organization.directory.utils

/**
 * A utility object containing network-related endpoints.
 */
object NetworkConstants {
    /**
     * The base URL for the API endpoints.
     */
    const val BASE_URL: String = "https://s3.amazonaws.com"

    /**
     * The endpoint for retrieving employee data.
     */
    const val EMPLOYEE_END_POINT = "/sq-mobile-interview/employees.json"

    /**
     * The endpoint for retrieving malformed employee data for testing.
     */
    const val MALFUNCTIONED_EMPLOYEE_END_POINT = "/sq-mobile-interview/employees_malformed.json"

    /**
     * The endpoint for retrieving empty employee data for testing.
     */
    const val EMPTY_EMPLOYEE_END_POINT = "/sq-mobile-interview/employees_empty.json"
}