package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivityListBinding
import com.karlgao.kotlintemplate.view.util.BaseActivity
import android.support.v7.widget.SimpleItemAnimator
import com.karlgao.kotlintemplate.util.sub
import com.karlgao.kotlintemplate.view.adapter.UserAdapter
import com.karlgao.kotlintemplate.vm.UserListVM
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import kotlin.properties.Delegates


/**
 * Recycler view sample
 *
 * Created by Karl on 23/10/17.
 */

class ListActivity : BaseActivity() {

    private val vm: UserListVM by lazy { UserListVM() }
    private val adapter: UserAdapter by lazy { UserAdapter(vm.vms) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)

        setSupportActionBar(toolbar)

        initList()
        initAction()
    }

    private fun initList() {
        rv.setHasFixedSize(true) // ?? test
        rv.layoutManager = LinearLayoutManager(this) // Or gridLayoutManager if needs grid
        (rv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false // ?? test
        rv.setEmptyView(srl_empty)
//        rv.setAttachedView(srl) // if you want to set any view that shows with recycler view

        srl.setColorSchemeColors(getColorRes(R.color.colorAccent))
        srl_empty.setColorSchemeColors(getColorRes(R.color.colorAccent))

        //adapter
        rv.adapter = adapter
    }

    private fun initAction() {
        srl.setOnRefreshListener { fetchData() }
        srl_empty.setOnRefreshListener { fetchData() }

        //onclick
    }

    private fun fetchData() {
        vm.getUsers().init()
                .sub {
                    srl.isRefreshing = false
                    srl_empty.isRefreshing = false
                    adapter.notifyDataSetChanged()
                }
    }

}