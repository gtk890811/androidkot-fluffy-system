package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.karlgao.kotlintemplate.AppConfig
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivityTabBinding
import com.karlgao.kotlintemplate.view.util.BaseActivity
import com.karlgao.kotlintemplate.view.util.ContainerFragment
import kotlinx.android.synthetic.main.activity_tab.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import timber.log.Timber

/**
 * Sample tab
 *
 * Created by Karl on 23/10/17.
 */

class TabActivity : BaseActivity() {

    private val imageArray: Array<Int> by lazy { arrayOf(R.drawable.ic_apps_24dp, R.drawable.ic_settings_24dp) }
    private val titleArray: Array<Int> by lazy { arrayOf(R.string.tab_a, R.string.tab_b) }
    private val activeColor: Int by lazy { getColorRes(R.color.colorAccent) }
    private val idleColor: Int by lazy { getColorRes(R.color.white) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityTabBinding>(this, R.layout.activity_tab)

        setSupportActionBar(toolbar)

        initTab()
    }

    private fun initTab() {
        (0 until imageArray.size).forEach { i ->
            val v = layoutInflater.inflate(R.layout.layout_tabitem, null)
            initTabView(v, i, false)
            tab.addTab(tab.newTab().setCustomView(v))
        }

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                onTab(tab)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // although we determine reselect action by ourselves, action may revoke this fun instead of onTabSelected
                onTab(tab)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        //onTabReselected is called here
        tab.getTabAt(0)?.select()
    }

    private fun onTab(tab: TabLayout.Tab?) {
        val position = tab?.position ?: 0
        syncSelection(position)
        showTab(position)
    }

    private fun syncSelection(position: Int) {
        (0 until imageArray.size).forEach { i ->
            val t = tab.getTabAt(i)
            initTabView(t?.customView, i, i == position)
        }
    }

    private fun initTabView(v: View?, position: Int, active: Boolean) {
        if (v == null) return

        val icon = v.findViewById<ImageView>(R.id.icon)
        val text = v.findViewById<TextView>(R.id.text)

        icon.setImageResource(imageArray[position])
        icon.setColorFilter(if (active) activeColor else idleColor, android.graphics.PorterDuff.Mode.SRC_IN)

        text.setText(titleArray[position])
        text.setTextColor(if (active) activeColor else idleColor)
    }

    override fun onBackPressed() {
        val containerFragment = getCurrentFragment() as ContainerFragment?
        if (containerFragment == null) super.onBackPressed()
        else if (!containerFragment.popBackStack()) super.onBackPressed()
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