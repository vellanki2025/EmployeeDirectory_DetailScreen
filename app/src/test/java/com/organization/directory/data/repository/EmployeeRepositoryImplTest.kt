package com.organization.directory.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.organization.directory.core.common.Outcome
import com.organization.directory.data.api.EmployeeApi
import com.organization.directory.data.entity.EmployeeDto
import com.organization.directory.data.entity.EmployeeListDto
import com.organization.directory.data.entity.EmployeeType
import com.organization.directory.data.mapper.EmployeeDtoMapper
import com.organization.directory.data.util.PhoneNumberUtilsWrapper
import com.organization.directory.domain.repository.EmployeeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class EmployeeRepositoryImplTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var employeeApi: EmployeeApi
    @Mock
    private lateinit var phoneNumberUtilsWrapper: PhoneNumberUtilsWrapper
    private lateinit var dtoMapper: EmployeeDtoMapper
    private lateinit var repository: EmployeeRepository

    @Before
    fun setUp() {
        dtoMapper = EmployeeDtoMapper(phoneNumberUtilsWrapper)
        repository = EmployeeRepositoryImpl(employeeApi, dtoMapper)
    }

    @Test
    fun `Get employee data from backend successfully`() {
        runTest {
            val inputNumber = "5553280123"
            val formattedNumber = "555-328-0123"

            //given
            val employeeListDto = EmployeeListDto(
                listOf(
                    EmployeeDto(
                        uuid = "0d8fcc12-4d0c-425c-8355-390b312b909c",
                        fullName = "Justine Mason",
                        phoneNumber = inputNumber,
                        emailAddress = "jmason.demo@squareup.com",
                        biography = "Engineer on the Point of Sale team.",
                        photoUrlSmall = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                        photoUrlLarge = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/large.jpg",
                        team = "Point of Sale",
                        employeeType = EmployeeType.FULL_TIME
                    )
                )
            )
            `when`(employeeApi.getEmployeeData()).thenReturn(employeeListDto)
            `when`(phoneNumberUtilsWrapper.formatNumber(inputNumber)).thenReturn(formattedNumber)

            // execute
            val employeeList = repository.getEmployeeData()

            // Verify
            assert(employeeList is Outcome.Success)
            assert((employeeList as Outcome.Success).data.size == 1)
        }
    }

    @Test
    fun `Failed to fetch employee data from backend`() {
        runTest {

            //given
            val employeeListDto = EmployeeListDto(emptyList())
            `when`(employeeApi.getEmployeeData()).thenReturn(employeeListDto)

            // execute
            val employeeList = repository.getEmployeeData()

            // Verify
            assert(employeeList is Outcome.Success)
            assert((employeeList as Outcome.Success).data.isEmpty())
        }
    }

}