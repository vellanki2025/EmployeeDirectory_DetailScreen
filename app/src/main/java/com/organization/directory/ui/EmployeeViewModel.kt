package com.organization.directory.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.organization.directory.core.common.CoroutineDispatchProvider
import com.organization.directory.core.common.EmployeeState
import com.organization.directory.core.common.Outcome
import com.organization.directory.data.entity.EmployeeDto
import com.organization.directory.domain.model.Employee
import com.organization.directory.domain.repository.EmployeeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for the [EmployeeFragment].
 * This ViewModel handles the network interaction and provides the Employee state to the UI.
 */
@HiltViewModel
class EmployeeViewModel @Inject constructor(
    private val repository: EmployeeRepository,
    private val dispatcherProvider: CoroutineDispatchProvider
) : ViewModel() {

    /**
     * LiveData to observe the [EmployeeState] of the data.
     */
    val employeeState: LiveData<EmployeeState> get() = _employeeState
    private val _employeeState = MutableLiveData<EmployeeState>()

    /**
     * LiveData to observe on [Employee] data.
     */
    val employeeDetails: LiveData<Employee?> get() = _employeeDetails
    private val _employeeDetails = MutableLiveData<Employee?>()

    /**
     * Initializes the ViewModel by fetching Employee data.
     */
    init {
        getEmployeeData()
    }

    /**
     * Fetches Employee data and pass it to UI in the [EmployeeState].
     */
    fun getEmployeeData() {
        _employeeState.postValue(EmployeeState.Loading)
        viewModelScope.launch(dispatcherProvider.io) {
            when (val result = repository.getEmployeeData()) {
                is Outcome.Success -> {
                    result.data.let {
                        if (it.isNotEmpty()) {
                            _employeeState.postValue(EmployeeState.Success(it))
                        } else {
                            _employeeState.postValue(
                                EmployeeState.Empty(null))
                        }
                    }
                }

                is Outcome.Error -> {
                    _employeeState.postValue(EmployeeState.Failure(result.message))
                }
            }
        }
    }

    fun getEmployeeDetails(id: String) {
        viewModelScope.launch(dispatcherProvider.io) {
            when (val result = repository.getEmployeeDetails(id)) {
                is Outcome.Success -> {
                    result.data.let {
                        _employeeDetails.postValue(it)
                    }
                }

                is Outcome.Error -> {
                    _employeeDetails.postValue(null)
                }
            }
        }
    }

}