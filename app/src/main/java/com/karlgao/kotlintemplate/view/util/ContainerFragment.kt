package com.karlgao.kotlintemplate.view.util

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.FragmentContainerBinding
import com.karlgao.kotlintemplate.view.ui.fragments.A1Fragment
import com.karlgao.kotlintemplate.view.ui.fragments.B1Fragment
import timber.log.Timber

/**
 * Container fragment that handles navigation for sub fragments for tab and drawer
 *
 * Created by Karl on 19/10/17.
 */

class ContainerFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            DataBindingUtil.inflate<FragmentContainerBinding>(inflater, R.layout.fragment_container, container, false).root


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt("position", 0) ?: 0
        Timber.d("current position $position")
        initSubFragment(position)
    }

    private fun initSubFragment(position: Int) {
        // Check whether the container fragment is empty or not
        val isEmpty = childFragmentManager.fragments == null || childFragmentManager.fragments.size == 0

        // Define root fragment for each position
        // Add bundle if any data need to be passed in
        val f: Fragment
        val b = Bundle()
        when (position) {
            0 -> f = A1Fragment()
            1 -> {
                f = B1Fragment()
                b.putString("test_key", "test_data")
                f.arguments = b
            }
            else -> f = A1Fragment()
        }

        //show the root fragment for the tab if it is empty
        if (isEmpty) {
            childFragmentManager.beginTransaction()
                    .setAnim()
                    .add(R.id.container, f, f.javaClass.name)
                    .commit()
        }
    }

    private fun FragmentTransaction.setAnim(): FragmentTransaction {
        return this.setCustomAnimations(R.anim.fade_in_100, R.anim.fade_out, R.anim.fade_in_100, R.anim.fade_out)
    }

    // Add a new fragment
    fun addFragment(f: Fragment) {
        childFragmentManager.beginTransaction()
                .setAnim()
                .replace(R.id.container, f, f.javaClass.name)
                .addToBackStack(f.javaClass.name)
                .commit()
    }

    // The return boolean indicates that whether there is enough stack to exit.
    // Returns true if it pop back stack and return to the last fragment
    // Returns false if the stack reached the root and the app should handle the back action on activity
    fun popBackStack(level: Int = 1): Boolean {
        for (i in 0 until level) {
            if (childFragmentManager.backStackEntryCount == 0) {
                return false
            }
            childFragmentManager.popBackStack()
        }
        return true
    }

    // For complex navigation, add a new fragment while remove several back stack
    // If the back stack does not have enough level to pop, it will behave as adding a new fragment as root
    fun popAdd(f: Fragment, level: Int = 1) {
        for (i in 0 until level) {
            if (childFragmentManager.backStackEntryCount == 0) {
                break
            }
            childFragmentManager.popBackStack()
        }
        childFragmentManager.beginTransaction()
                .setAnim()
                .replace(R.id.container, f, f.javaClass.name)
                .addToBackStack(f.javaClass.name)
                .commit()
    }
}