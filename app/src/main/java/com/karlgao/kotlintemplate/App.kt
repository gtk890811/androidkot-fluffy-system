package com.karlgao.kotlintemplate

import android.app.Application
import com.karlgao.kotlintemplate.AppConfig.isLogEnabled
import com.karlgao.kotlintemplate.dagger.component.AppComponent
import com.karlgao.kotlintemplate.dagger.component.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import com.karlgao.kotlintemplate.dagger.module.AppModule
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

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

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