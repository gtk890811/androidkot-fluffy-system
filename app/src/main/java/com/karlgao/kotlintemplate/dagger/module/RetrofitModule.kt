package com.karlgao.kotlintemplate.dagger.module

import com.fasterxml.jackson.databind.ObjectMapper
import com.karlgao.kotlintemplate.AppConfig
import com.karlgao.kotlintemplate.dagger.qualifier.BasicAuth
import com.karlgao.kotlintemplate.dagger.qualifier.TokenAuth
import com.karlgao.kotlintemplate.data.network.WebService
import com.karlgao.kotlintemplate.data.preference.MainPrefs
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


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
    @BasicAuth
    fun provideBasicAuth(): WebService {
        return getRetrofit(null).create(WebService::class.java)
    }

    @Provides
    @Singleton
    @TokenAuth
    fun provideTokenAuth(prefs: MainPrefs): WebService {
        return getRetrofit(prefs).create(WebService::class.java)
    }

    /**
     * Retrofit Configuration
     */
    private fun getRetrofit(prefs: MainPrefs?): Retrofit {
        return Retrofit.Builder()
                .baseUrl(AppConfig.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapper()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(getOKHttpClient(prefs))
                .build()
    }

    /**
     * OKHttpClient Configuration
     */
    private fun getOKHttpClient(prefs: MainPrefs?): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(getHeaderInterceptor(prefs))
                .addInterceptor(getLoggingInterceptor())
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build()
    }

    /**
     * Log Interceptor Configuration
     */
    private fun getLoggingInterceptor(): Interceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (AppConfig.ENABLE_LOG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return logging
    }

    /**
     * Header Interceptor Configuration
     */
    private fun getHeaderInterceptor(prefs: MainPrefs?): Interceptor {
        return Interceptor {
            val basicRequest = it.request().newBuilder()
                    .addHeader("content-type", "application/json")
                    .build()
            val tokenRequest = it.request().newBuilder()
                    .addHeader("content-type", "application/json")
                    .addHeader("authorization", "Bearer " + prefs?.accessToken)
                    .build()
            it.proceed(if (prefs == null) basicRequest else tokenRequest)
        }
    }
}