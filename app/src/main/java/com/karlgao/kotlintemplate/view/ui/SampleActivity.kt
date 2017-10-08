package com.karlgao.kotlintemplate.view.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.util.PermissionUtil
import com.karlgao.kotlintemplate.view.util.BaseActivity
import com.karlgao.kotlintemplate.vm.SampleVM
import kotlinx.android.synthetic.main.activity_list.*
import timber.log.Timber

class SampleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)


        var vm1 = SampleVM()
        var vm2 = SampleVM()
        vm1.dm.temp = "vm1"
        vm2.dm.temp = "vm2"

        askForPermission(
                PermissionUtil.ACCESS_FINE_LOCATION,
                PermissionUtil.ACCESS_COARSE_LOCATION,
                permissionResult = object : PermissionResult {
                    override fun permissionGranted() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }

                    override fun permissionDenied() {
                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            Timber.d(vm1.dm.temp)
        }
    }
}
