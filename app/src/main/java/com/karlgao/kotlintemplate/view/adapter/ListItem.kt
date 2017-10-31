package com.karlgao.kotlintemplate.view.adapter

import android.content.Context
import com.karlgao.kotlintemplate.vm.UserVM
import com.karlgao.kotlintemplate.vm.util.BaseVM

/**
 * Created by dev on 31/10/17.
 */

data class ListItem<out T : BaseVM>(val item: T, val type: Int = 0)