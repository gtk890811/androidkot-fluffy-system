package com.karlgao.kotlintemplate.view.vh

import com.karlgao.kotlintemplate.databinding.ListitemUserBinding
import com.karlgao.kotlintemplate.view.util.BaseViewHolder
import com.karlgao.kotlintemplate.vm.UserVM

/**
 * User view holder that implements base view holder
 *
 * Created by Karl on 2/11/17.
 */

class UserVH(val binding: ListitemUserBinding, clickEvent: (position: Int) -> Unit, otherEvents: Array<out (position: Int) -> Unit>) : BaseViewHolder<UserVM>(binding.root) {

    init {
        binding.swipeTop.setOnClickListener { clickEvent.invoke(layoutPosition) }
    }

    override fun bind(item: UserVM) {
        binding.user = item
        binding.tvName.text = item.fullName.get()
    }
}