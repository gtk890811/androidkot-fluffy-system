package com.karlgao.kotlintemplate.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Base Adapter for recycler view
 *
 * Created by Karl on 26/10/17.
 */

class BaseAdapter<S, T : BaseViewHolder>(private val context: Context, private val items: MutableList<S>) : RecyclerView.Adapter<T>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
