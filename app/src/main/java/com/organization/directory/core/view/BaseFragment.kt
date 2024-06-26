package com.organization.directory.core.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Implement shared functionality across multiple Fragment
 */
abstract class BaseFragment<T : ViewDataBinding>(@LayoutRes val layoutResourceId: Int) :
    Fragment(layoutResourceId) {

    /**
     * Root view of the fragment.
     */
    private lateinit var mRootView: View

    /**
     * Data binding instance for this fragment's layout.
     */
    protected lateinit var binding: T

    /**
     * Called to create the view hierarchy associated with the fragment.
     *
     * @param inflater The [LayoutInflater] object that used to inflate any views in the fragment.
     * @param container Parent view that the fragment's UI will be attached to, this can be null.
     * @param savedInstanceState This fragment is being re-constructed from a previous saved state as given here if not null.
     * @return Root view of the fragment's layout.
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        mRootView = binding.root
        mRootView.isClickable = true
        mRootView.isFocusable = true
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleArguments(savedInstanceState)
        setUpFragmentUI(mRootView)
    }

    abstract fun handleArguments(savedInstanceState: Bundle?)

    /**
     * Set up the UI of the fragment.
     *
     * @param view The root view of the fragment's layout.
     */
    abstract fun setUpFragmentUI(view: View?)

    /**
     * Called when the fragment's view is being destroyed.
     * Removes the root view from its parent view if it has one.
     */
    override fun onDestroyView() {
        if (mRootView.parent != null) {
            (mRootView.parent as ViewGroup).removeView(mRootView)
        }
        super.onDestroyView()
    }
}