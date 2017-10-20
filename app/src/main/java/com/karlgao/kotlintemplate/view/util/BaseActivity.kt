@file:Suppress("DEPRECATION")

package com.karlgao.kotlintemplate.view.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.karlgao.kotlintemplate.AppConfig


/**
 * Base activity class
 *
 * Created by Karl on 2/10/17.
 */

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity(), ContextInterface {

    companion object {
        private const val PERMISSION_REQUEST = 200
    }

    override val ba: BaseActivity
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        portrait()
        adjustPan()
    }

    protected fun fullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    protected fun portrait(){
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    protected fun landscape(){
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    protected fun adjustPan(){
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }
    protected fun adjustResize(){
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    protected fun setStatusBarColor(@ColorRes res: Int){
        AppConfig.isLollipopOrAbove {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(res)
        }
    }

    protected fun setNavigationBarColor(@ColorRes res: Int){
        AppConfig.isLollipopOrAbove {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.navigationBarColor = resources.getColor(res)
        }
    }

    //Progress Dialog (Deprecated. Try not to use this. Use inline ProgressBar instead)
    private val pd: ProgressDialog by lazy {
        ProgressDialog(this)
    }

    fun showPD(message: String = "Please wait...") {
        pd.setMessage(message)
        pd.setCanceledOnTouchOutside(false)
        pd.show()
    }

    fun dismissPD() {
        pd.dismiss()
    }

    //Permissions
    lateinit private var permissionGranted: () -> Unit
    lateinit private var permissionDenied: () -> Unit

    fun askForPermission(vararg permissions: String, permissionGranted: () -> Unit, permissionDenied: () -> Unit) {
        this.permissionGranted = permissionGranted
        this.permissionDenied = permissionDenied
        permissions.filter { !isPermissionGranted(it) }
        if (permissions.isEmpty() || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGranted.invoke()
        } else {
            requestPermissions(permissions, PERMISSION_REQUEST)
        }
    }

    private fun isPermissionGranted(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST) {
            val granted = grantResults.any { grantResults.isNotEmpty() && it == PackageManager.PERMISSION_GRANTED }

            if (granted) {
                permissionGranted.invoke()
            } else {
                permissionDenied.invoke()
            }
        }
    }
}