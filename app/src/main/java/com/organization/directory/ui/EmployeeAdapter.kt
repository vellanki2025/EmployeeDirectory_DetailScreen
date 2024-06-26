package com.organization.directory.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.organization.directory.R
import com.organization.directory.databinding.LayoutEmployeeBinding
import com.organization.directory.domain.model.Employee
import com.organization.directory.domain.model.RelayHandler
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

/**
 * The [EmployeeAdapter] class is responsible for managing the list of employees and
 * providing a view for each employee in a RecyclerView.
 */
@ActivityRetainedScoped
class EmployeeAdapter @Inject constructor() :
    RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder>() {

    companion object {

        private const val IMAGE_HEIGHT = 80
        private const val IMAGE_WIDTH = 80

        private val callback = object : DiffUtil.ItemCallback<Employee>() {
            override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
                return oldItem == newItem
            }
        }
    }

    private val differ = AsyncListDiffer(this, callback)

    /**
     * Set the list of employees to be displayed in the adapter.
     *
     * @param list The list of employees to be displayed.
     */
    fun setList(list: List<Employee>) {
        differ.submitList(list)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder =
        EmployeeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.layout_employee, parent, false
            )
        )

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    /**
     * The `EmployeeViewHolder` class represents a single view item for an employee in the RecyclerView.
     *
     * @param binding The [LayoutEmployeeBinding] instance associated with this view holder.
     */
    class EmployeeViewHolder(private val binding: LayoutEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the data of an employee to the view.
         * Sets the employee's full name / phone number / email address
         *
         * @param employee The [Employee] object containing employee information.
         */
        fun bind(employee: Employee) {
            binding.apply {
                loadImage(employeeImage, employee.photoUrlSmall)

                textName.text = employee.fullName
                if (employee.phoneNumber.isNotEmpty()) {
                    textPhone.text = employee.phoneNumber
                } else {
                    textPhone.visibility = View.GONE
                }
                if (employee.emailAddress.isNotEmpty()) {
                    textEmail.text = employee.emailAddress
                } else {
                    textEmail.visibility = View.GONE
                }

                // add listener
                root.setOnClickListener {
                    RelayHandler.payLoadHandler.accept(Pair("employee", employee))
                }
            }
        }

        /**
         * Loads an image into an ImageView using the Glide library.
         *
         * @param view The ImageView in which the image will be displayed.
         * @param imageUrl The URL of the image to load.
         */
        @SuppressLint("CheckResult")
        private fun loadImage(view: ImageView, imageUrl: String?) {
            val requestOptions = RequestOptions()
            requestOptions.placeholder(R.drawable.ic_launcher_background)
            requestOptions.error(R.drawable.ic_launcher_background)

            Glide.with(view.context)
                .load(imageUrl?.toUri())
                .apply(requestOptions)
                .apply(RequestOptions().override(IMAGE_WIDTH, IMAGE_HEIGHT))
                .into(view)
        }
    }
}