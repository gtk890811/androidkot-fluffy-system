package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.view.Menu
import android.view.MenuItem
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivitySampleBinding
import com.karlgao.kotlintemplate.util.PermissionUtil
import com.karlgao.kotlintemplate.view.util.BaseActivity
import com.karlgao.kotlintemplate.vm.SampleVM
import kotlinx.android.synthetic.main.activity_sample.*
import timber.log.Timber

/**
 * Sample Activity
 *
 * Created by Karl on 17/10/17.
 */

//extends base activity
class SampleActivity : BaseActivity() {

    //init view model
    private val vm :SampleVM by lazy { SampleVM() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //change layout option
        fullScreen()
        portrait()
        adjustPan()


        //handle data passed through intent

        //init binding
        val binding = DataBindingUtil.setContentView<ActivitySampleBinding>(this, R.layout.activity_sample)
        binding.sample = vm

        //init keyboard hiding
        autoDismissKeyboard(cl_root)

        //init actionbar
        setSupportActionBar(toolbar)

        //set status bar and navigation bar color if necessary
        setStatusBarColor(R.color.colorPrimaryDark)
        setNavigationBarColor(R.color.colorPrimaryDark)

        initView()
        initAction()
    }

    private fun initView(){
        //init action bar, init view ...
        askForPermission(
                PermissionUtil.WRITE_EXTERNAL_STORAGE,
                permissionGranted = {
                    Timber.d("Permission Granted")
                },
                permissionDenied = {
                    Timber.d("Permission Denied")
                }
        )
    }

    private fun initAction(){
        //init button click listener...
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()

            showPD()
            Handler().postDelayed({ dismissPD() }, 2000)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        //init action bar https://developer.android.com/training/appbar/action-views.html

        return super.onCreateOptionsMenu(menu)
    }

    //handle action button action
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            R.id.action0 -> true
            R.id.action_bar -> {
                //do something
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
