package com.karlgao.kotlintemplate.view.vh

import com.karlgao.kotlintemplate.databinding.ListitemLoaderBinding
import com.karlgao.kotlintemplate.view.util.BaseViewHolder
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Loader view holder
 *
 * Created by Karl on 2/11/17.
 */

class LoaderVH(val binding: ListitemLoaderBinding) : BaseViewHolder<BaseVM>(binding.root) {
    override fun bind(item: BaseVM) {

    }
}
