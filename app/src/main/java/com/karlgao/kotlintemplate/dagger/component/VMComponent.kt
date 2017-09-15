package com.karlgao.kotlintemplate.dagger.component

import com.karlgao.kotlintemplate.dagger.scope.PerVM
import dagger.Component

/**
 * Injection registration for each view model
 *
 * Created by Karl on 15/9/17.
 */

@PerVM
@Component(dependencies = AppComponent.class)
interface VMComponent {

}