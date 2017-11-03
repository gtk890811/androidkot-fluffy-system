package com.karlgao.kotlintemplate.view.vh

import com.karlgao.kotlintemplate.databinding.ListitemUserBinding
import com.karlgao.kotlintemplate.view.util.BaseViewHolder
import com.karlgao.kotlintemplate.vm.UserVM

/**
 * Sample view holder that implements base view holder, uses UserVH as an example
 *
 * Created by Karl on 2/11/17.
 */


// Include at least the clickEvent listener and implement it in init.
// If multiple clickEvent is needed, use the below comment constructor
// Define event type in an companion object                            ----------------------+
//                                                                                           |
//                                                                                           |
//                                                                                           V
// class UserVH(val binding: ListitemUserBinding, clickEvent: ((position: Int) -> Unit)?, otherEvents: ((event: String, position: Int) -> Unit)?) : BaseViewHolder<UserVM>(binding.root) {

class SampleVH(val binding: ListitemUserBinding, clickEvent: ((position: Int) -> Unit)?) : BaseViewHolder<UserVM>(binding.root) {

    // click event should be done in constructor
    init {
        binding.swipeTop.setOnClickListener { clickEvent?.invoke(layoutPosition) }
    }

    // change data in bind
    override fun bind(item: UserVM) {
        binding.user = item
        binding.tvName.text = item.fullName.get()
    }

    // clear garbage when recycled if needed
    override fun recycle() {
    }
}