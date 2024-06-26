package com.organization.directory.domain.model

/**
 * Data class representing an [Employee].
 *
 * @property fullName The full name of the employee.
 * @property phoneNumber The phone number of the employee.
 * @property emailAddress The email address of the employee.
 * @property biography A brief biography or description of the employee.
 * @property photoUrlSmall The URL of the employee's small-sized photo.
 * @property team The team or department to which the employee belongs.
 */
data class Employee(
    val uuid: String,
    val fullName: String,
    val phoneNumber: String,
    val emailAddress: String,
    val biography: String,
    val photoUrlSmall: String,
    val photoUrlLarge: String,
    val team: String
)