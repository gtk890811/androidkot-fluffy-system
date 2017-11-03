@file:Suppress("DEPRECATION")

package com.karlgao.kotlintemplate.view.util

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.support.annotation.ColorRes
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.karlgao.kotlintemplate.AppConfig
import com.karlgao.kotlintemplate.R
import timber.log.Timber


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

    protected fun fullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    protected fun portrait() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    protected fun landscape() {
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    protected fun adjustPan() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    protected fun adjustResize() {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE or WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    protected fun getStatusBarHeight(): Int {
        var statusHeight = 24.dpToPx()
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            statusHeight = resources.getDimensionPixelSize(resourceId)
            Timber.d("Status bar height: ${statusHeight.pxToDp()} dp")
        }
        return statusHeight
    }

    protected fun getNavigationBarHeight(): Int {
        var navigationHeight = 48.dpToPx()
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            navigationHeight = resources.getDimensionPixelSize(resourceId)
            Timber.d("Navigation bar height: ${navigationHeight.pxToDp()} dp")
        }
        return navigationHeight
    }

    // pass in the toolbar if you want to extend the toolbar under the status bar
    protected fun setStatusBarTranslucent(toolbar: Toolbar? = null) {
        AppConfig.isLollipopOrAbove {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            if (toolbar != null) {
                extendViewUnderStatusBar(toolbar)
            }
        }
    }

    protected fun extendViewUnderStatusBar(view: View) {
        val statusHeight = getStatusBarHeight()
        val toolbarHeight = 56.dpToPx()
        view.layoutParams.height = toolbarHeight + statusHeight
        view.setPadding(view.paddingLeft,
                view.paddingTop + statusHeight,
                view.paddingRight,
                view.paddingBottom)
    }

    protected fun setStatusBarColor(@ColorRes res: Int) {
        AppConfig.isLollipopOrAbove {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = getColorRes(res)
        }
    }

    protected fun setNavigationBarTranslucent(tab: TabLayout? = null) {
        AppConfig.isLollipopOrAbove {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)

            if (tab != null) {
                extendViewUnderNavigationBar(tab)
            }
        }
    }

    protected fun extendViewUnderNavigationBar(view: View) {
        val navHeight = getNavigationBarHeight()
        val tabHeight = 56.dpToPx()
        view.layoutParams.height = tabHeight + navHeight
        view.setPadding(view.paddingLeft,
                view.paddingTop,
                view.paddingRight,
                view.paddingBottom + navHeight)
    }

    protected fun setNavigationBarColor(@ColorRes res: Int) {
        AppConfig.isLollipopOrAbove {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.navigationBarColor = getColorRes(res)
        }
    }

    protected fun setNoLimitWindow() {
        AppConfig.isLollipopOrAbove {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
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