package com.karlgao.kotlintemplate.view.util

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.LinearLayoutManager
import com.karlgao.kotlintemplate.AppConfig


/**
 * Endless Scroll listener
 *
 * Created by Karl on 2/11/17.
 */

abstract class EndlessScrollListener : RecyclerView.OnScrollListener(){

    private var previousTotal = 0
    private var isLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (isLoading) {
            if (totalItemCount > previousTotal) {
                isLoading = false
                previousTotal = totalItemCount
            }
        }
        if (!isLoading && totalItemCount - visibleItemCount <= firstVisibleItem + AppConfig.VISIBLE_THRESHOLD) {
            // End has been reached

            onLoadMore()

            isLoading = true
        }
    }

    abstract fun onLoadMore()
}
