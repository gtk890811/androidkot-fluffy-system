package com.karlgao.kotlintemplate.vm

import android.databinding.ObservableField
import com.karlgao.kotlintemplate.data.DataManager
import com.karlgao.kotlintemplate.model.business.SampleDataClass
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.vm.util.BaseVM
import timber.log.Timber
import javax.inject.Inject

/**
 * ViewModels should be created as follow
 *
 * Created by Karl on 10/10/17.
 */

// constructor to allow create view model from model or scratch
class SampleVM (val model: SampleDataClass = SampleDataClass()) : BaseVM() {

    // inject datamanager
    @Inject
    lateinit var dm: DataManager

    // injection and initialize field with model
    init {
        vmComponent.inject(this)
        initVM()
    }

    // fields that will be used in the view(xml)
    val normalField: ObservableField<String> = ObservableField()
    val normalField2: ObservableField<String> = ObservableField()

    private fun initVM(){
        normalField.set(model.normalField)
        normalField2.set(model.normalField2)
    }

    // this is optional, required when the app needs to save the model or etc., e.g. store object in realm
    // the fields in model needs to be var to be able to modified.
    private fun syncModel(): SampleDataClass {
        model.normalField = normalField.get()
        return model
    }

    // functions with business logic
    fun printToken() {
        Timber.d(dm.mainPrefs.accessToken)
    }
}


// list view model example
// no constructor is required in normal circumstance
class SampleListVM: BaseVM() {

    // inject datamanager
    @Inject
    lateinit var dm: DataManager

    // injection
    init {
        vmComponent.inject(this)
    }

    // view model list
    val vms: List<SampleDataClass> = ArrayList()

    // functions with business logic
    fun getSampleData(){

    }
}