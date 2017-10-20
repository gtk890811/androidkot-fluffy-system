package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.media.Image
import android.os.Bundle
import com.karlgao.kotlintemplate.AppConfig
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivityMainBinding
import com.karlgao.kotlintemplate.view.util.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.startActivity


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        setSupportActionBar(toolbar)

        initAction()
    }

    private fun initAction() {
        bt_drawer.setOnClickListener { startActivity<DrawerActivity>() }

        bt_tab.setOnClickListener { startActivity<TabActivity>() }

        bt_list.setOnClickListener { startActivity<ListActivity>() }

        bt_image.setOnClickListener { startActivity<ImageActivity>() }

        bt_browser.setOnClickListener { browse(AppConfig.BASE_URL) }

        bt_pn.setOnClickListener { startActivity<PNActivity>() }
    }
}