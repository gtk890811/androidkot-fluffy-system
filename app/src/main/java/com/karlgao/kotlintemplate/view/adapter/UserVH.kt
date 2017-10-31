package com.karlgao.kotlintemplate.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ListitemUserBinding
import com.karlgao.kotlintemplate.vm.UserVM
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Created by dev on 27/10/17.
 */

class UserVH(val binding: ListitemUserBinding) : BaseViewHolder<ListitemUserBinding>(binding) {

    init {
        binding.swipeTop.setOnClickListener { onClick.invoke() }
    }

    override fun getLayoutId(): Int = R.layout.listitem_user

    override fun <K : BaseVM> bind(item: K) {
        binding.user = item as UserVM
        binding.tvName.text = item.fullName.get()

    }
}