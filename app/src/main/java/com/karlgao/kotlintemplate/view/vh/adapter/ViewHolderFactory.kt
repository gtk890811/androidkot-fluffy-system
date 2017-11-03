package com.karlgao.kotlintemplate.view.vh.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.ViewGroup
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.view.util.BaseViewHolder
import com.karlgao.kotlintemplate.view.vh.LoaderVH
import com.karlgao.kotlintemplate.view.vh.UserVH
import com.karlgao.kotlintemplate.vm.UserVM
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Factory interface that provides two methods that will be used in mainAdapter
 *
 * Links view model with their layout id
 * Creates view holder from layout id
 *
 * Created by Karl on 2/11/17.
 */

interface ViewHolderFactory {

    fun getLayoutId(vm: BaseVM): Int {
        return when (vm) {

            is UserVM -> R.layout.listitem_user

        // ...

            else -> R.layout.listitem_loader
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : BaseVM> getViewHolder(parent: ViewGroup, viewType: Int, clickEvent: ((position: Int) -> Unit)?, otherEvents: ((event: String, position: Int) -> Unit)?): BaseViewHolder<T> {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        val holder = when (viewType) {

            R.layout.listitem_user -> {
                UserVH(DataBindingUtil.inflate(inflater, R.layout.listitem_user, parent, false), clickEvent, otherEvents)
            }

        // ...

            // use loader view holder as default.
            else -> LoaderVH(DataBindingUtil.inflate(inflater, R.layout.listitem_loader, parent, false))
        }

        // this is where the unchecked cast happens
        // e.g. casting BaseViewHolder<UserVM> to BaseViewHolder<BaseVM>
        return holder as BaseViewHolder<T>
    }
}
