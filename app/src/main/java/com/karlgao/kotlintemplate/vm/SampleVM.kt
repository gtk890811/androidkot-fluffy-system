package com.karlgao.kotlintemplate.vm

import com.karlgao.kotlintemplate.data.DataManager
import com.karlgao.kotlintemplate.model.business.SampleDataClass
import com.karlgao.kotlintemplate.vm.util.BaseVM
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by dev on 12/9/17.
 */
class SampleVM : BaseVM() {

    @Inject
    lateinit var dm: DataManager

    lateinit var mMSample: SampleDataClass

    init {
        vmComponent.inject(this)
    }

    fun printToken() {
        Timber.d(dm.mainPrefs.accessToken)
    }
}