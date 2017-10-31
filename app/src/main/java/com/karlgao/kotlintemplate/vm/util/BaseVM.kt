package com.karlgao.kotlintemplate.vm.util

import com.karlgao.kotlintemplate.App
import com.karlgao.kotlintemplate.dagger.component.DaggerVMComponent
import com.karlgao.kotlintemplate.dagger.component.VMComponent
import com.karlgao.kotlintemplate.view.adapter.ListItem

/**
 * Base view model, provides component for injection
 *
 * Created by Karl on 25/9/17.
 */

open class BaseVM {

    protected val vmComponent: VMComponent by lazy {
        DaggerVMComponent.builder()
                .appComponent(App.instance.appComponent)
                .build()
    }

    fun <T : BaseVM> wrap(vms: MutableList<T>, type: Int = 0): MutableList<ListItem<T>> {
        return vms.map { item -> ListItem(item, type) }
                .toMutableList()
    }
}