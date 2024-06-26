package com.organization.directory.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.organization.directory.core.util.Constants

/**
 * Data transfer object (DTO) representing an Employee.
 *
 * @property uuid The unique identifier for the Employee.
 * @property fullName The full name of the Employee.
 * @property phoneNumber The phone number of the Employee.
 * @property emailAddress The email address of the Employee.
 * @property biography A brief biography or description of the Employee.
 * @property photoUrlSmall The URL of the small-sized photo of the Employee.
 * @property photoUrlLarge The URL of the large-sized photo of the Employee.
 * @property team The team or department to which the Employee belongs.
 * @property employeeType The type of the Employee, defined by the [EmployeeType] enum.
 */

//@Entity(Constants.EMPLOYEE_TABLE_NAME) // no need of Entity when there is no database
//data class EmployeeDto(
//    @SerializedName("uuid")
//    @PrimaryKey  val uuid: String, // no need of primary key when there is no database
//
@Entity(Constants.EMPLOYEE_TABLE_NAME)
data class EmployeeDto(
    @SerializedName("uuid")
    @PrimaryKey  val uuid: String,

    @SerializedName("full_name")
    val fullName: String?,

    @SerializedName("phone_number")
    val phoneNumber: String?,

    @SerializedName("email_address")
    val emailAddress: String?,

    @SerializedName("biography")
    val biography: String?,

    @SerializedName("photo_url_small")
    val photoUrlSmall: String?,

    @SerializedName("photo_url_large")
    val photoUrlLarge: String?,

    @SerializedName("team")
    val team: String?,

    @SerializedName("employee_type")
    val employeeType: EmployeeType?
)

/**
 * Enum class representing the type of an Employee.
 */
enum class EmployeeType {
    FULL_TIME, PART_TIME, CONTRACTOR
}
