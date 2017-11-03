package com.karlgao.kotlintemplate.view.vh.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.view.util.BaseViewHolder
import com.karlgao.kotlintemplate.view.vh.LoaderVH
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Base Adapter for recycler view
 *
 * Created by Karl on 26/10/17.
 */

class RecyclerAdapter<T : BaseVM>(private val items: MutableList<T>) : RecyclerView.Adapter<BaseViewHolder<T>>(), ViewHolderFactory {

    // flags
    var isEndlessScrollEnabled = false
    private var loaderIsVisible = false

    fun setLoaderVisible(visible: Boolean) {
        if (isEndlessScrollEnabled) {
            if (!loaderIsVisible && visible) {
                notifyItemInserted(items.size)
            }
            if (loaderIsVisible && !visible) {
                notifyItemChanged(items.size)
            }
            loaderIsVisible = visible
        }
    }

    // Override function
    override fun getItemCount(): Int {
        return if (loaderIsVisible) {
            items.size + 1
        } else {
            items.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (loaderIsVisible && position == items.size) {
            return R.layout.listitem_loader
        }
        return getLayoutId(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T> {

        return getViewHolder(parent, viewType, clickEvent, otherEvents)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        if (position >= items.size) {
            (holder as LoaderVH).bind(BaseVM())
        } else {
            holder.bind(items[position])
        }
    }

    override fun onViewRecycled(holder: BaseViewHolder<T>) {
        holder.recycle()
    }

    // Events
    private var clickEvent: ((position: Int) -> Unit)? = null
    private var otherEvents: ((event: String, position: Int) -> Unit)? = null

    fun onItemClick(event: (position: Int) -> Unit) {
        clickEvent = event
    }

    fun onItemEvent(events: (event: String, position: Int) -> Unit) {
        otherEvents = events
    }
}
