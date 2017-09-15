package com.karlgao.kotlintemplate

import android.app.Application
import com.karlgao.kotlintemplate.dagger.component.AppComponent
import com.karlgao.kotlintemplate.dagger.component.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import com.karlgao.kotlintemplate.dagger.module.AppModule




/**
 * Application class
 */
class App: Application() {

    private lateinit var app: App
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        app = this
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()


        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
                .name("fluffy_kot.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfiguration)
    }

    // dagger
    fun get(): App {
        return app
    }

    fun getComponent(): AppComponent {
        return appComponent
    }
}