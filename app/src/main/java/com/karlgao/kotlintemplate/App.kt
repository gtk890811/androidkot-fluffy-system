package com.karlgao.kotlintemplate

import android.app.Application
import com.karlgao.kotlintemplate.dagger.component.AppComponent
import com.karlgao.kotlintemplate.dagger.component.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import com.karlgao.kotlintemplate.dagger.module.AppModule


/**
 * Application class
 *
 * Created by Karl on 15/9/17.
 */

class App : Application() {

    private val app: App = this
    private val appComponent: AppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .name("fluffy_kot.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

}