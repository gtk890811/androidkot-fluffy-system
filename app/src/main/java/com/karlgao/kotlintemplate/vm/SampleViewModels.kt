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
 * Name conventions: Just put the name convention rules suggested here as I don't know where else is suitable
 * Note it is just suggested.
 * XML: lowercase with underscore e.g. tv_route_no
 * Java/Kotlin: camel-case e.g. routeNo
 *
 * Created by Karl on 10/10/17.
 */

// constructor to allow create view model from model or scratch
class SampleVM (val model: SampleDataClass = SampleDataClass()) : BaseVM() {

    // inject datamanager
    @Inject
    lateinit var dm: DataManager

    // fields that will be used in the view(xml)
    val normalField: ObservableField<String> = ObservableField()
    val normalField2: ObservableField<String> = ObservableField()

    // injection and initialize field with model
    init {
        vmComponent.inject(this)
        initVM()
    }

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
        Timber.d(dm.prefs.accessToken)
    }
}


// list view model example
// no constructor is required in normal circumstance
class SampleListVM: BaseVM() {

    // inject datamanager
    @Inject
    lateinit var dm: DataManager

    // view model list
    val vms: List<SampleDataClass> = ArrayList()

    // injection
    init {
        vmComponent.inject(this)
    }

    // functions with business logic
    fun getSampleData(){

    }
}