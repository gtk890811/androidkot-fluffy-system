package com.karlgao.kotlintemplate.data

import com.karlgao.kotlintemplate.data.network.WebServiceManager
import com.karlgao.kotlintemplate.data.preference.MainPrefs
import com.karlgao.kotlintemplate.data.realm.RealmManager
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A place that links all data provider together and provides data to view models
 * Can also be used to save temp data for vm
 * e.g. a reference of a view model that will be shared in different activities/fragments
 *
 * Created by Karl on 18/9/17.
 */

@Singleton
class DataManager
    @Inject constructor(val web: WebServiceManager,
                        val realm: RealmManager,
                        val prefs: MainPrefs){

    var temp: String = "not initialized"

}