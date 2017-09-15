package com.karlgao.kotlintemplate.data.realm

import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by dev on 12/9/17.
 */
@Singleton
class RealmManager {

    var realm: Realm? = null

    @Inject
    fun RealmManager(realm: Realm){
        this.realm = realm
    }

}