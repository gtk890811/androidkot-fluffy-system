package com.karlgao.kotlintemplate.data.realm

import io.realm.Realm
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Queries for realm database
 *
 * Created by Karl on 18/9/17.
 */

@Singleton
class RealmManager
@Inject constructor(private val realm: Realm) {

}