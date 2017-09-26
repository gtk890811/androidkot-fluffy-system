package com.karlgao.kotlintemplate

import android.util.Log

/**
 * Stores flags and info for different configuration
 * (Normally we have debug, staging and release)
 * e.g. log switch, base url
 *
 * Created by Karl on 18/9/17.
 */

object AppConfig {

    //General Configuration

    val GENERAL_TAG: String = "FluffyKotlin"

    val IMAGE_DIR_NAME: String = "FluffyKotlin"
    val IMAGE_SIZE: Int = 1000
    val IMAGE_COMPRESS_QUALITY: Int = 80

    val PASSWORD_LENGTH: Int = 6

    //Build type based configuration

    private const val BUILD_TYPE: String = BuildConfig.BUILD_TYPE

    val BASE_URL: String
    val ENABLE_LOG: Boolean
    val EXTERNAL_STORAGE: Boolean
    val DISABLE_AUTO_LOGIN: Boolean
    val PREFILL_CRENDENTIAL: Boolean

    init {
        when (BUILD_TYPE) {

            "debug" -> {
                BASE_URL = "https://private-bb38f-androidmvvmtemplate.apiary-mock.com/"
                ENABLE_LOG = true
                EXTERNAL_STORAGE = true
                DISABLE_AUTO_LOGIN = true
                PREFILL_CRENDENTIAL = true
            }

            "release" -> {
                BASE_URL = "https://private-bb38f-androidmvvmtemplate.apiary-mock.com/"
                ENABLE_LOG = false
                EXTERNAL_STORAGE = false
                DISABLE_AUTO_LOGIN = false
                PREFILL_CRENDENTIAL = false
            }

            else -> {
                Log.wtf(GENERAL_TAG,"Build Type '$BUILD_TYPE' not recognized, the app won't work.")
                BASE_URL = "https://www.google.com/"
                ENABLE_LOG = false
                EXTERNAL_STORAGE = false
                DISABLE_AUTO_LOGIN = false
                PREFILL_CRENDENTIAL = false
            }
        }
    }
}