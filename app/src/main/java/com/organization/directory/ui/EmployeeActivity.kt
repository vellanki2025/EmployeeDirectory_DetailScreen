package com.organization.directory.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.organization.directory.R
import com.organization.directory.core.view.BaseActivity
import com.organization.directory.databinding.ActivityMainBinding
import com.organization.directory.domain.model.Employee
import com.organization.directory.domain.model.RelayHandler
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * Main activity to host the UI
 */
@AndroidEntryPoint
class EmployeeActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private var clickDisposable: Disposable? = null

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController

    override fun setUpActivityUI(intent: Intent, savedInstanceState: Bundle?) {
        setUpNavHost()
    }

    /**
     * Sets up the navigation host fragment for the application.
     */
    private fun setUpNavHost() {
        navHostFragment = NavHostFragment.create(R.navigation.navigation_graph)
        supportFragmentManager.beginTransaction().replace(
            binding.fragment.id, navHostFragment
        ).setPrimaryNavigationFragment(navHostFragment).commit()
    }

    override fun onResume() {
        super.onResume()
        clickDisposable = RelayHandler.payLoadHandler.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                payload -> handleRelayPayload(payload)
            }
    }

    private fun handleRelayPayload(options: Pair<String, Any?>) {
        if (options.first == "employee") {
            Toast.makeText(this, "employee name ${(options.second as Employee).fullName}", Toast.LENGTH_SHORT).show()
            val bundle = Bundle().apply {
                putString("myArgument", (options.second as Employee).uuid)
            }
            navigateToEmployeeDetailView(bundle)
        }
    }

    /**
     * Navigate to employee detail view
     */
    private fun navigateToEmployeeDetailView(bundle: Bundle) {
        navController = navHostFragment.navController
        navController.navigate(R.id.employeeFragmentDetail, bundle)
    }

    override fun onPause() {
        super.onPause()
        clickDisposable?.dispose()
        clickDisposable = null
    }
}
