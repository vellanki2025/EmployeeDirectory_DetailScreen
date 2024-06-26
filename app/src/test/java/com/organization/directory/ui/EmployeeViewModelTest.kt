package com.organization.directory.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.organization.directory.core.common.CoroutineDispatchProvider
import com.organization.directory.core.common.EmployeeState
import com.organization.directory.core.common.Outcome
import com.organization.directory.domain.model.Employee
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
class EmployeeViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var employeeRepository: EmployeeRepository
    private lateinit var dispatcherProvider: CoroutineDispatchProvider
    private lateinit var viewModel: EmployeeViewModel

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()
        viewModel = EmployeeViewModel(employeeRepository, dispatcherProvider)
    }

    @Test
    fun `Given employee list, check for success state`() {
        runTest {

            //given
            val employees = listOf(
                Employee(
                    fullName = "Justine Mason",
                    phoneNumber = "5553280123",
                    emailAddress = "jmason.demo@squareup.com",
                    biography = "Engineer on the Point of Sale team.",
                    photoUrlSmall = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                    team = "Point of Sale"
                )
            )

            `when`(employeeRepository.getEmployeeData()).thenReturn(Outcome.Success(employees))

            //execute
            viewModel.getEmployeeData()

            //test
            val employeeState = viewModel.employeeState.value
            assert(employeeState is EmployeeState.Success)
        }
    }

    @Test
    fun `Given empty employee list, check for Empty state`() {
        runTest {

            //given
            `when`(employeeRepository.getEmployeeData()).thenReturn(Outcome.Success(emptyList<Employee>()))

            //execute
            viewModel.getEmployeeData()

            //test
            val employeeState = viewModel.employeeState.value
            assert(employeeState is EmployeeState.Empty)
        }
    }

    @Test
    fun `Given empty employee list, check for Failed state`() {
        runTest {

            //given
            `when`(employeeRepository.getEmployeeData()).thenReturn(Outcome.Error(message = "error"))

            //execute
            viewModel.getEmployeeData()

            //test
            val employeeState = viewModel.employeeState.value
            assert(employeeState is EmployeeState.Failure)
        }
    }
}