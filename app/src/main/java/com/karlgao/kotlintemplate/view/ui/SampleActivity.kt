package com.karlgao.kotlintemplate.view.ui

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.util.PermissionUtil
import com.karlgao.kotlintemplate.view.util.BaseActivity
import kotlinx.android.synthetic.main.activity_sample.*
import timber.log.Timber

class SampleActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample)
        setSupportActionBar(toolbar)

        askForPermission(
                PermissionUtil.WRITE_EXTERNAL_STORAGE,
                permissionGranted = {
                    Timber.i("Permission Granted")
                },
                permissionDenied = {
                    Timber.i("Permission Denied")
                }
        )



        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            showPD()
            Handler().postDelayed({ dismissPD() }, 2000)

        }
    }
}
