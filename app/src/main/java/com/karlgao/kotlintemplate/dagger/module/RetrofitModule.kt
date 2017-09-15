package com.karlgao.kotlintemplate.dagger.module

import com.karlgao.kotlintemplate.data.network.WebServiceManager
import dagger.Module
import javax.inject.Singleton
import dagger.Provides

/**
 * Module that provides Web Service object
 * including basic one and the one with token
 *
 * Created by Karl on 15/9/17.
 */

@Module
class RetrofitModule {
    @Provides
    @Singleton
    internal fun provideWebServiceManager(): WebServiceManager? {
        return null
    }
}