package com.karlgao.kotlintemplate

/**
 * Stores flags and info for different configuration
 * (Normally we have debug, staging and release)
 * e.g. log switch, base url
 *
 * Created by Karl on 18/9/17.
 */

object AppConfig {

    val BuildType: String = BuildConfig.BUILD_TYPE
    val BaseUrl: String = "asdasdw"

}