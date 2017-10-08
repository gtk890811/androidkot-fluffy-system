package com.karlgao.kotlintemplate.view.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.karlgao.kotlintemplate.R


/**
 * Base activity class
 *
 * Created by Karl on 2/10/17.
 */

@SuppressLint("Registered")
open class BaseActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_REQUEST = 200
    }

    //Progress Dialog


    //Permissions
    lateinit private var permissionResult: PermissionResult

    interface PermissionResult {
        fun permissionGranted()

        fun permissionDenied()
    }

    fun askForPermission(vararg permissions: String, permissionResult: PermissionResult) {
        this.permissionResult = permissionResult
        permissions.filter { !isPermissionGranted(it) }
        if (permissions.isEmpty() || Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionResult.permissionGranted()
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
                permissionResult.permissionGranted()
            } else {
                permissionResult.permissionDenied()
            }
        }
    }

    //Util functions
    protected fun hideStatusBar() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    fun hideSoftKeyboard() {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocus.windowToken, 0)
            currentFocus.clearFocus()
        }
    }

    fun autoDismissKeyboard(view: View) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideSoftKeyboard()
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            (0 until view.childCount)
                    .map { view.getChildAt(it) }
                    .forEach { autoDismissKeyboard(it) }
        }
    }

    fun hasInternetConnection(): Boolean{
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    //Util functions that needs Context
    inline fun isTablet(func: () -> Unit) {
        if (resources.getBoolean(R.bool.is_tablet)) {
            func()
        }
    }

    fun Int.dpToPx(): Int {
        val density = resources.displayMetrics.density
        return (this * density).toInt()
    }

    fun Int.pxToDp(): Int {
        val density = resources.displayMetrics.density
        return (this / density).toInt()
    }

}