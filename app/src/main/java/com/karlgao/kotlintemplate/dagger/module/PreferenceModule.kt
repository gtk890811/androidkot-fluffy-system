package com.karlgao.kotlintemplate.dagger.module

import android.content.Context
import dagger.Module
import com.karlgao.kotlintemplate.data.preference.MainPrefs
import com.karlgao.kotlintemplate.dagger.qualifier.ApplicationContext
import javax.inject.Singleton
import dagger.Provides



/**
 * Module that provides preference object
 *
 * Created by Karl on 15/9/17.
 */

@Module
class PreferenceModule {
    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): MainPrefs {
        return MainPrefs.get(context)
    }
}