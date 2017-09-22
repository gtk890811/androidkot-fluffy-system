package com.karlgao.kotlintemplate.vm

import com.karlgao.kotlintemplate.App
import com.karlgao.kotlintemplate.dagger.component.DaggerVMComponent
import com.karlgao.kotlintemplate.data.DataManager
import com.karlgao.kotlintemplate.vm.util.BaseVM
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dev on 12/9/17.
 */
class SampleVM : BaseVM() {

    @Inject
    lateinit var dm: DataManager

    init {
        var vmComponent = DaggerVMComponent.builder()
                .appComponent(App.instance.appComponent)
                .build()
        vmComponent.inject(this)
        dm.mainPrefs.accessToken = "fuckasdasd"
    }

    fun printToken() {
        Timber.d(dm.mainPrefs.accessToken)
    }
}