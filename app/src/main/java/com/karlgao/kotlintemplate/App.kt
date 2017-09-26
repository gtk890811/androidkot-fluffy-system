package com.karlgao.kotlintemplate

import android.app.Application
import com.karlgao.kotlintemplate.dagger.component.AppComponent
import com.karlgao.kotlintemplate.dagger.component.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import com.karlgao.kotlintemplate.dagger.module.AppModule
import com.karlgao.kotlintemplate.util.isLogEnabled
import timber.log.Timber


/**
 * Application class
 *
 * Created by Karl on 15/9/17.
 */

class App : Application() {

    companion object {
        lateinit var instance: App private set
    }

    lateinit var appComponent: AppComponent private set

    override fun onCreate() {
        super.onCreate()

        instance = this

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()

        //Timber Setup
        isLogEnabled { Timber.plant(Timber.DebugTree()) }

        //Realm Setup
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .name("fluffy_kot.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

}