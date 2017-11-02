package com.karlgao.kotlintemplate.view.util

import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * A data class that can be used to wrap different view models into one list
 * item: view model itself
 * type: identifier for same view model that has different layout requirements
 */

data class ListItem<out T : BaseVM>(val item: T, val type: Int = 0)