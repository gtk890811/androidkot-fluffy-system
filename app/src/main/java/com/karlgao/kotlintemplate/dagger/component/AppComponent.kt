package com.karlgao.kotlintemplate.dagger.component

import com.karlgao.kotlintemplate.dagger.module.AppModule
import com.karlgao.kotlintemplate.dagger.module.PreferenceModule
import com.karlgao.kotlintemplate.dagger.module.RealmModule
import com.karlgao.kotlintemplate.dagger.module.RetrofitModule
import com.karlgao.kotlintemplate.data.network.WebServiceManager
import com.karlgao.kotlintemplate.data.preference.PrefsKeysPrefs
import com.karlgao.kotlintemplate.data.realm.RealmManager
import dagger.Component
import javax.inject.Singleton

/**
 * Component that registers different data provider
 *
 * Created by Karl on 15/9/17.
 */

@Singleton
@Component(modules = arrayOf(AppModule::class, PreferenceModule::class, RetrofitModule::class, RealmModule::class))
interface AppComponent {

//    fun provideWebServiceManager(): WebServiceManager
    fun providePrefs(): PrefsKeysPrefs
//    fun provideRealm(): RealmManager

}
