package com.organization.directory.data.mapper

import com.organization.directory.data.entity.EmployeeDto
import com.organization.directory.data.entity.EmployeeListDto
import com.organization.directory.data.entity.EmployeeType
import com.organization.directory.data.util.PhoneNumberUtilsWrapper
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EmployeeDtoMapperTest {

    @Mock
    private lateinit var phoneNumberUtilsWrapper: PhoneNumberUtilsWrapper
    private lateinit var dtoMapper: EmployeeDtoMapper

    @Before
    fun setUp() {
        dtoMapper = EmployeeDtoMapper(phoneNumberUtilsWrapper)
    }

    @Test
    fun `Map server response successfully`() {

        //given
        val fullName = "Justine Mason"
        val emailAddress = "jmason.demo@squareup.com"
        val inputNumber = "5553280123"
        val formattedNumber = "555-328-0123"

        val employeeListDto = EmployeeListDto(
            listOf(
                EmployeeDto(
                    uuid = "0d8fcc12-4d0c-425c-8355-390b312b909c",
                    fullName = fullName,
                    phoneNumber = inputNumber,
                    emailAddress = emailAddress,
                    biography = "Engineer on the Point of Sale team.",
                    photoUrlSmall = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                    photoUrlLarge = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
                    team = "Point of Sale",
                    employeeType = EmployeeType.FULL_TIME
                )
            )
        )
        `when`(phoneNumberUtilsWrapper.formatNumber(inputNumber)).thenReturn(formattedNumber)

        // execute
        val result = dtoMapper.mapFromEntity(employeeListDto)

        // verify
        assert(result.size == 1)
        assertEquals(result[0].fullName, fullName)
        assertEquals(result[0].emailAddress, emailAddress)
        assertEquals(result[0].phoneNumber, formattedNumber)
    }

    @Test
    fun `Map empty server response successfully`() {

        //given
        val employeeListDto = EmployeeListDto(emptyList())

        // execute
        val result = dtoMapper.mapFromEntity(employeeListDto)

        // verify
        assert(result.isEmpty())
    }
}