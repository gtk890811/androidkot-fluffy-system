package com.karlgao.kotlintemplate.view.util

import android.support.v7.widget.RecyclerView
import android.view.View
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Base View Holder
 *
 * Created by Karl on 27/10/17.
 */

abstract class BaseViewHolder<in T : BaseVM>(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun bind(item: T)

    open fun recycle() {}
}
