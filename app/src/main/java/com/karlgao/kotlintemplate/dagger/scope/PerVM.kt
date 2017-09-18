package com.karlgao.kotlintemplate.dagger.scope

import javax.inject.Scope

/**
 * Custom Scope for VMComponent
 *
 * Created by Karl on 18/9/17.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class PerVM