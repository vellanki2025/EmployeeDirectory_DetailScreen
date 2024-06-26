package com.organization.directory.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.organization.directory.R
import com.organization.directory.core.common.EmployeeState
import com.organization.directory.core.view.BaseFragment
import com.organization.directory.databinding.FragmentEmployeesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main fragment to show list of employees and different states
 *
 * Implement onClick listener
 * Pass the employee UUID
 * open a detail fragment
 * fetch the actual employee object from DB
 * update the UI
 *
 */
@AndroidEntryPoint
class EmployeeFragment : BaseFragment<FragmentEmployeesBinding>(R.layout.fragment_employees) {

    private val viewModel: EmployeeViewModel by activityViewModels()

    @Inject
    lateinit var employeeAdapter: EmployeeAdapter

    override fun handleArguments(savedInstanceState: Bundle?) {
        // Handle data from bundle
    }

    override fun setUpFragmentUI(view: View?) {
        setUpRecyclerView()
        configureObservers()
    }

    /**
     * Sets up the RecyclerView to display the list of employees.
     * set EmployeeAdapter as the adapter for the RecyclerView.
     */
    private fun setUpRecyclerView() {
        binding.rvEmployees.adapter = employeeAdapter
    }

    /**
     * Configures observers to react to changes in the view model's state and
     * react accordingly to its different states.
     */
    private fun configureObservers() {
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getEmployeeData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        lifecycleScope.launch {
            viewModel.employeeState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is EmployeeState.Loading -> {
                        binding.progressBarCircular.isVisible = true
                        binding.rvEmployees.isVisible = false
                    }

                    is EmployeeState.Success -> {
                        binding.progressBarCircular.isVisible = false
                        binding.rvEmployees.isVisible = true
                        binding.textInvalid.isVisible = false
                        employeeAdapter.setList(state.data)
                    }

                    is EmployeeState.Empty -> {
                        binding.progressBarCircular.isVisible = false
                        showEmptyResults(state.noResultsMessage)
                        binding.rvEmployees.isVisible = false
                    }

                    is EmployeeState.Failure -> {
                        binding.progressBarCircular.isVisible = false
                        binding.rvEmployees.isVisible = false
                        showErrorMessage(state.errorMessage)
                    }
                }
            }
        }
    }

    /**
     * Showing error message to the user
     */
    private fun showErrorMessage(errorMessage: String?) {
        val displayMessage = errorMessage ?: context?.getString(R.string.error_msg_fetching_data)
        binding.textInvalid.apply {
            isVisible = true
            text = displayMessage
        }
    }

    /**
     * Showing empty results message to the user
     */
    private fun showEmptyResults(defaultMessage: String?) {
        val displayMessage = defaultMessage ?: context?.getString(R.string.error_msg_for_empty_list)
        binding.textInvalid.apply {
            isVisible = true
            text = displayMessage
        }
    }
}
