package com.organization.directory.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.organization.directory.R
import com.organization.directory.core.view.BaseFragment
import com.organization.directory.databinding.FragmentEmployeeDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class EmployeeDetailFragment :
    BaseFragment<FragmentEmployeeDetailBinding>(R.layout.fragment_employee_detail) {

    private val args: EmployeeDetailFragmentArgs by navArgs()
    private lateinit var employeeUuid: String

    private val viewModel: EmployeeViewModel by activityViewModels()

    override fun handleArguments(savedInstanceState: Bundle?) {
        employeeUuid = args.myArgument
    }

    override fun setUpFragmentUI(view: View?) {
        configureObservers()
        viewModel.getEmployeeDetails(employeeUuid)
    }

    private fun configureObservers() {
        lifecycleScope.launch {
            viewModel.employeeDetails.observe(viewLifecycleOwner) { employeeDetails ->
                employeeDetails?.let {
                    loadImage(employeeDetails.photoUrlLarge)
                    binding.employeeFullName.text = employeeDetails.fullName
                    binding.employeeEmailId.text = employeeDetails.emailAddress
                } ?: run {
                    showErrorMessage()
                }
            }
        }
    }

    private fun loadImage(imageUrl: String?) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.drawable.ic_launcher_background)
        requestOptions.error(R.drawable.ic_launcher_background)
        context?.let {
            Glide.with(it)
                .load(imageUrl?.toUri())
                .apply(requestOptions)
                .into(binding.employeePhoto)
        }
    }

    private fun showErrorMessage() {
        val displayMessage = context?.getString(R.string.error_msg_fetching_data)
        Toast.makeText(context, "employee name $displayMessage", Toast.LENGTH_SHORT).show()
    }

}