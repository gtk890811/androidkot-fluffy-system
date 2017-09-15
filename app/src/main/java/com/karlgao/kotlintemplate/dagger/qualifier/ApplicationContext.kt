package com.karlgao.kotlintemplate.dagger.qualifier

import javax.inject.Qualifier

/**
 * Qualifier for separating Application Context from Activity Context
 *
 * Created by Karl on 15/9/17.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext