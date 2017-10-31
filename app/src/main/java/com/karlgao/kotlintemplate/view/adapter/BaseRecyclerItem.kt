package com.karlgao.kotlintemplate.view.adapter

import android.support.v7.widget.RecyclerView

/**
 * Created by dev on 27/10/17.
 */

abstract class BaseRecyclerItem<out T,out E: RecyclerView.ViewHolder> {

    // returns layout id
    abstract fun type(): Int

    abstract fun item(): T

    abstract fun vh(): E

    abstract fun render()

}