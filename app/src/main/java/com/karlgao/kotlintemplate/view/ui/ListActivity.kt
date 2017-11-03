package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SimpleItemAnimator
import com.karlgao.kotlintemplate.AppConfig
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivityListBinding
import com.karlgao.kotlintemplate.util.success
import com.karlgao.kotlintemplate.view.util.BaseActivity
import com.karlgao.kotlintemplate.view.vh.UserVH.Companion.EVENT_DELETION
import com.karlgao.kotlintemplate.view.vh.adapter.RecyclerAdapter
import com.karlgao.kotlintemplate.vm.UserListVM
import com.karlgao.kotlintemplate.vm.UserVM
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.jetbrains.anko.toast
import java.util.*


/**
 * Recycler view sample
 *
 * Created by Karl on 23/10/17.
 */

class ListActivity : BaseActivity() {

    private val vm: UserListVM by lazy { UserListVM() }
    private val adapter: RecyclerAdapter<UserVM> by lazy { RecyclerAdapter(vm.vms) }

    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityListBinding>(this, R.layout.activity_list)

        setSupportActionBar(toolbar)

        setNavigationBarTranslucent()

        initView()
        initList()
        initAction()
    }

    private fun initView() {
        extendViewUnderStatusBar(toolbar)
        extendViewUnderNavigationBar(rv)
        //set up fab transparent navigation bar
        val navHeight = getNavigationBarHeight()
        val marginBottom = (fab.layoutParams as CoordinatorLayout.LayoutParams).bottomMargin
        (fab.layoutParams as CoordinatorLayout.LayoutParams).bottomMargin = marginBottom + navHeight

        rv.clipToPadding = false
    }

    private fun initList() {
        rv.setHasFixedSize(true) // enable this for better performance
        rv.layoutManager = LinearLayoutManager(this) // Or gridLayoutManager if needs grid
        (rv.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = true // disable the change animation(flash) when you call adapter.notifyItemChange
        rv.setEmptyView(srl_empty)
//        rv.setAttachedView(srl) // if you want to set any view that shows with recycler view

        srl.setColorSchemeColors(getColorRes(R.color.colorAccent))
        srl_empty.setColorSchemeColors(getColorRes(R.color.colorAccent))

        //adapter
        rv.adapter = adapter
        adapter.isEndlessScrollEnabled = true
    }

    private fun initAction() {
        srl.setOnRefreshListener { fetchData(false) }
        srl_empty.setOnRefreshListener { fetchData(false) }

        if (adapter.isEndlessScrollEnabled) {
            rv.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemCount = recyclerView.childCount
                    val totalItemCount = recyclerView.layoutManager.itemCount
                    val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (!isLoading && (totalItemCount - visibleItemCount <= firstVisibleItem + AppConfig.VISIBLE_THRESHOLD)) {
                        // End has been reached

                        fetchData(true)

                        isLoading = true
                    }
                }
            })
        }

        //onclick
        adapter.onItemClick { position ->
            toast("Item position: $position")
        }


        adapter.onItemEvent({ event, position ->
            when (event) {
                EVENT_DELETION -> {
                    vm.removeVM(position)
                    adapter.notifyItemRemoved(position)
                }
            }
        })

        fab.setOnClickListener {
            val firstVisibleItem = (rv.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
            val lastVisibleItem = (rv.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
            val range = lastVisibleItem - firstVisibleItem - 2
            val lucky = if (range > 0) Random().nextInt(range) else 0
            val position = firstVisibleItem + 1 + lucky

            vm.addNewVM(position)
            adapter.notifyItemInserted(position)

            toast("Item added at position $position")
        }
    }

    private fun fetchData(isLoadMore: Boolean) {
        //needs rework on this
        val sizeBefore = vm.vms.size
        vm.getUsers(isLoadMore).init()
                .doOnError{
                    srl.isRefreshing = false
                    srl_empty.isRefreshing = false
                    isLoading = false
                }
                .success {
                    srl.isRefreshing = false
                    srl_empty.isRefreshing = false
                    isLoading = false

                    val sizeAfter = vm.vms.size

                    endlessScrollImpl(adapter, sizeBefore, sizeAfter, isLoadMore, vm.isLastPage())
                }
    }

}