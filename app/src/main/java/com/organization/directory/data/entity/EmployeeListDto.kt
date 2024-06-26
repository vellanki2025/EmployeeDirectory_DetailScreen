package com.organization.directory.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Data transfer object (DTO) representing a list of employees.
 */
data class EmployeeListDto(
    @SerializedName("employees") val employees: List<EmployeeDto>,
)