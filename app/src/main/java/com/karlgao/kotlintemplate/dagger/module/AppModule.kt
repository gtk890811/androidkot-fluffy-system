package com.karlgao.kotlintemplate.dagger.module

import android.app.Application
import android.content.Context
import com.karlgao.kotlintemplate.App
import dagger.Module
import com.karlgao.kotlintemplate.dagger.qualifier.ApplicationContext
import dagger.Provides



/**
 * Module that provides Application and Application Context
 *
 * Created by Karl on 15/9/17.
 */

@Module
class AppModule(private val mApp: App) {

    @Provides
    fun provideApplication(): Application {
        return mApp
    }

    @Provides
    @ApplicationContext
    fun provideContext(): Context {
        return mApp.applicationContext
    }

}