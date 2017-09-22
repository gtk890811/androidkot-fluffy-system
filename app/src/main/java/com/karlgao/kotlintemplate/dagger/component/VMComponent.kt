package com.karlgao.kotlintemplate.dagger.component

import com.karlgao.kotlintemplate.dagger.scope.PerVM
import com.karlgao.kotlintemplate.vm.SampleVM
import dagger.Component

/**
 * Injection registration for each view model
 *
 * Created by Karl on 15/9/17.
 */

@PerVM
@Component(dependencies = arrayOf(AppComponent::class))
interface VMComponent {
    fun inject(vm: SampleVM)
}