package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import com.karlgao.kotlintemplate.AppConfig
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivityDrawerBinding
import com.karlgao.kotlintemplate.view.util.BaseActivity
import com.karlgao.kotlintemplate.view.util.ContainerFragment
import kotlinx.android.synthetic.main.activity_drawer.*
import kotlinx.android.synthetic.main.layout_drawer.*
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Drawer layout sample
 *
 * Created by Karl on 23/10/17.
 */

class DrawerActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityDrawerBinding>(this, R.layout.activity_drawer)

        setStatusBarTranslucent()
        extendViewUnderStatusBar(toolbar)

        setSupportActionBar(toolbar)

        initDrawer()
        initDrawerAction()
    }

    private fun initDrawer() {
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, 0, 0)
        drawer.addDrawerListener(toggle)
        toggle.syncState()

        showTab(0)
    }

    private fun initDrawerAction() {
        fl_item1.setOnClickListener {
            closeDrawer()
            showTab(0)
        }

        fl_item2.setOnClickListener {
            closeDrawer()
            showTab(1)
        }
    }

    private fun closeDrawer(): Boolean {
        return if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
            true
        } else false
    }

    override fun onBackPressed() {
        if (!closeDrawer()) {
            val containerFragment = getCurrentFragment() as ContainerFragment?
            if (containerFragment == null) super.onBackPressed()
            else if (!containerFragment.popBackStack()) super.onBackPressed()
        }
    }

    private fun showTab(position: Int) {
        val currentFragment = getCurrentFragment()
        val currentTag = currentFragment?.tag ?: ""

        // retrieve target fragment, null if it has not been added, uses position as a tag
        val targetTag = position.toString()
        val targetFragment = supportFragmentManager.findFragmentByTag(targetTag)

        // check if the fragment manager is empty
        val isEmpty = supportFragmentManager.fragments == null || supportFragmentManager.fragments.size == 0
        val isReselect = currentTag == targetTag

        // if reselect is not supported, and this is a reselect action, do nothing and return
        if (isReselect && !AppConfig.SUPPORT_RESELECT_TAB) {
            return
        }

        //begin transaction
        val ft = supportFragmentManager.beginTransaction()

        ft.setCustomAnimations(R.anim.fade_in_100, R.anim.fade_out, R.anim.fade_in_100, R.anim.fade_out)

        if (!isEmpty) {
            if (currentFragment != null) {
                if (isReselect) {
                    ft.remove(currentFragment)
                } else {
                    ft.detach(currentFragment)
                }
            }
        }

        // if the target fragment is not added, add a new one.
        // if this is a reselect action, add a new one anyway.
        if (targetFragment == null || isReselect) {
            val f = ContainerFragment()
            val bundle = Bundle()
            bundle.putInt("position", position)
            f.arguments = bundle
            ft.add(R.id.fragment, f, targetTag)
        } else {
            ft.attach(targetFragment)
        }

        ft.commit()
    }

    private fun getCurrentFragment(): Fragment? {
        return supportFragmentManager.fragments.firstOrNull { it != null && it.isVisible }
    }
}