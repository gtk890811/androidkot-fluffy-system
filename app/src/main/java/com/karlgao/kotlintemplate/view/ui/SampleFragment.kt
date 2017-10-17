package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivitySampleBinding
import com.karlgao.kotlintemplate.databinding.FragmentA1Binding
import com.karlgao.kotlintemplate.util.PermissionUtil
import com.karlgao.kotlintemplate.view.util.BaseFragment
import com.karlgao.kotlintemplate.vm.SampleVM
import kotlinx.android.synthetic.main.activity_sample.*
import timber.log.Timber


/**
 * Sample fragment, it is similar with sample activity
 *
 * Created by Karl on 17/10/17.
 */

//extends base fragment
class SampleFragment : BaseFragment() {

    //init view model
    val vm : SampleVM by lazy { SampleVM() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<ActivitySampleBinding>(inflater, R.layout.activity_sample, container, false)
        binding.sample = vm

        initView()
        initAction()

        return root
    }

    private fun initView(){
        //init action bar, init view ...
        mainActivity.askForPermission(
                PermissionUtil.WRITE_EXTERNAL_STORAGE,
                permissionGranted = {
                    Timber.i("Permission Granted")
                },
                permissionDenied = {
                    Timber.i("Permission Denied")
                }
        )
    }

    private fun initAction(){
        //init button click listener...
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            mainActivity.showPD()
            Handler().postDelayed({ mainActivity.showPD() }, 2000)
        }
    }
}
