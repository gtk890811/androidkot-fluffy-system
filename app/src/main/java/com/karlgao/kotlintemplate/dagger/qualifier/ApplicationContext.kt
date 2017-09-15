package com.karlgao.kotlintemplate.dagger.qualifier

import java.lang.annotation.RetentionPolicy
import javax.inject.Qualifier



/**
 * Created by dev on 12/9/17.
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext