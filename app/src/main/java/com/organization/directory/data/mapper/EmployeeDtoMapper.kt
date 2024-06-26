package com.organization.directory.data.mapper

import android.util.Log
import com.google.i18n.phonenumbers.NumberParseException
import com.organization.directory.data.entity.EmployeeDto
import com.organization.directory.data.entity.EmployeeListDto
import com.organization.directory.data.util.PhoneNumberUtilsWrapper
import com.organization.directory.domain.mapper.IEntityMapper
import com.organization.directory.domain.model.Employee
import javax.inject.Inject

/**
 * Mapper class to convert an [EmployeeListDto] entity to a list of [Employee] domain models.
 * This class implements the [IEntityMapper] interface.
 */
class EmployeeDtoMapper @Inject constructor(
    private val phoneNumberUtilsWrapper: PhoneNumberUtilsWrapper
) : IEntityMapper<List<EmployeeDto>, List<Employee>> {

    override fun mapFromEntity(entity: List<EmployeeDto>): List<Employee> {
        val employees: List<Employee> = entity.map {
            val formattedNumber = getFormattedNumber(it.phoneNumber.orEmpty())
            Employee(
                uuid = it.uuid,
                fullName = it.fullName.orEmpty(),
                phoneNumber = formattedNumber,
                emailAddress = it.emailAddress.orEmpty(),
                biography = it.biography.orEmpty(),
                photoUrlSmall = it.photoUrlSmall.orEmpty(),
                photoUrlLarge = it.photoUrlLarge.orEmpty(),
                team = it.team.orEmpty()
            )
        }
        return employees
    }

    /**
     * Formats the given phone number
     *
     * @param number The phone number to be formatted.
     * @return The formatted phone number.
     */
    private fun getFormattedNumber(number: String): String {
        var formattedPhoneNumber = number
        try {
            formattedPhoneNumber = phoneNumberUtilsWrapper.formatNumber(number).toString()
        } catch (e: NumberParseException) {
            Log.d("NumberParseException", " was thrown: $e")
        }
        return formattedPhoneNumber
    }
}