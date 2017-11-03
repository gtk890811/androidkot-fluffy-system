package com.karlgao.kotlintemplate.view.vh

import com.daimajia.swipe.SwipeLayout
import com.karlgao.kotlintemplate.databinding.ListitemUserBinding
import com.karlgao.kotlintemplate.view.util.BaseViewHolder
import com.karlgao.kotlintemplate.vm.UserVM

/**
 * User view holder that implements base view holder
 *
 * Created by Karl on 2/11/17.
 */

class UserVH(val binding: ListitemUserBinding, clickEvent: ((position: Int) -> Unit)?, otherEvents: ((event: String, position: Int) -> Unit)?) : BaseViewHolder<UserVM>(binding.root) {

    companion object {
        val EVENT_DELETION: String = "event_deletion"
    }

    init {
        binding.swipeTop.setOnClickListener { clickEvent?.invoke(layoutPosition) }
        binding.swipeBot.setOnClickListener { otherEvents?.invoke(EVENT_DELETION, layoutPosition) }
        binding.swipe.isClickToClose = true
    }

    override fun bind(item: UserVM) {
        binding.user = item
        binding.tvName.text = item.fullName.get()
    }

    override fun recycle() {
        binding.swipe.close(false)
    }

    fun closeSwipeView(){
        binding.swipe.close()
    }
}