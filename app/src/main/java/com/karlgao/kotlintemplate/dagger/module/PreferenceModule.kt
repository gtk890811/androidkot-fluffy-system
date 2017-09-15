package com.karlgao.kotlintemplate.dagger.module

import android.content.Context
import dagger.Module
import com.karlgao.kotlintemplate.data.preference.PrefsKeysPrefs
import com.karlgao.kotlintemplate.dagger.qualifier.ApplicationContext
import javax.inject.Singleton
import dagger.Provides



/**
 * Created by dev on 12/9/17.
 */

@Module
class PreferenceModule {
    @Provides
    @Singleton
    fun providePrefs(@ApplicationContext context: Context): PrefsKeysPrefs {
        return PrefsKeysPrefs.get(context)
    }
}