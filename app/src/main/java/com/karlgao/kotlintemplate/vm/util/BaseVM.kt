package com.karlgao.kotlintemplate.vm.util

import com.karlgao.kotlintemplate.App
import com.karlgao.kotlintemplate.dagger.component.DaggerVMComponent
import com.karlgao.kotlintemplate.dagger.component.VMComponent

/**
 * Created by dev on 12/9/17.
 */
open class BaseVM {

    protected val vmComponent: VMComponent = DaggerVMComponent.builder()
            .appComponent(App.instance.appComponent)
            .build()
}