package com.karlgao.kotlintemplate.dagger.qualifier

import javax.inject.Qualifier

/**
 * Qualifier for separating Token Auth Web Service from Basic Auth Web Service
 *
 * Created by Karl on 15/9/17.
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class TokenAuth