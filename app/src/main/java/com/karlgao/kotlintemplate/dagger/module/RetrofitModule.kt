package com.karlgao.kotlintemplate.dagger.module

import com.karlgao.kotlintemplate.data.network.WebServiceManager
import dagger.Module
import javax.inject.Singleton
import dagger.Provides



/**
 * Created by dev on 12/9/17.
 */

@Module
class RetrofitModule {
    @Provides
    @Singleton
    internal fun provideWebServiceManager(): WebServiceManager? {
        return null
    }
}