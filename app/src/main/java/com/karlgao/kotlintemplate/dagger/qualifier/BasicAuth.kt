package com.karlgao.kotlintemplate.dagger.qualifier

import javax.inject.Qualifier

/**
 * Qualifier for separating Basic Auth Web Service from Token Auth Web Service
 *
 * Created by Karl on 15/9/17.
 */

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class BasicAuth