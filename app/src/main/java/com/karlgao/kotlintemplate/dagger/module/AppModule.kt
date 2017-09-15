package com.karlgao.kotlintemplate.dagger.module

import android.app.Application
import android.content.Context
import com.karlgao.kotlintemplate.App
import dagger.Module
import com.karlgao.kotlintemplate.dagger.qualifier.ApplicationContext
import dagger.Provides



/**
 * Created by dev on 12/9/17.
 */

@Module
class AppModule(private val mApp: App) {

    @Provides
    internal fun provideApplication(): Application {
        return mApp
    }

    @Provides
    @ApplicationContext
    internal fun provideContext(): Context {
        return mApp.applicationContext
    }

}