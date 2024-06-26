package com.organization.directory.core.view

import android.content.Intent
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/*
 * Implement shared functionality across multiple Activities
 */
abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutResourceId: Int) :
    AppCompatActivity() {

    /**
     * The data binding instance for this activity's layout.
     */
    protected lateinit var binding: T

    /**
     * Called when the activity is created to setup UI
     *
     * @param savedInstanceState The saved instance state bundle.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResourceId)
        setUpActivityUI(intent, savedInstanceState)
    }

    /**
     * Set up the UI of the activity.
     *
     * @param intent The [Intent] associated with the activity.
     * @param savedInstanceState The saved instance state bundle.
     */
    abstract fun setUpActivityUI(intent: Intent, savedInstanceState: Bundle?)

}