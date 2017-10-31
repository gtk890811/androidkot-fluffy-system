package com.karlgao.kotlintemplate.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Base Adapter for recycler view
 *
 * Created by Karl on 26/10/17.
 */

abstract class BaseAdapter<T : BaseVM>(private val items: MutableList<T>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    // override this if different view is needed
    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return createVH(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    abstract fun createVH(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder

    abstract fun bindVH(holder: RecyclerView.ViewHolder, position: Int)
}
