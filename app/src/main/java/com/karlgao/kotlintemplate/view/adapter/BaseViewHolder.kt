package com.karlgao.kotlintemplate.view.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.View
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Base View Holder
 *
 * Created by Karl on 27/10/17.
 */

abstract class BaseViewHolder<T : ViewDataBinding>(binding: T) : RecyclerView.ViewHolder(binding.root) {

    abstract fun getLayoutId(): Int

    lateinit protected var onClick: () -> Unit

    fun onItemClick(event: () -> Unit) {
        onClick = event
    }

    abstract fun <K : BaseVM> bind(item: K)
}
