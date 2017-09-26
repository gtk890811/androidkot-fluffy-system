package com.karlgao.kotlintemplate.util

import android.os.Build
import com.karlgao.kotlintemplate.AppConfig

/**
 * Contains general Util Method
 *
 * Created by Karl on 25/9/17.
 */

fun isLollipopOrAbove(func: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        func()
    }
}

fun isLogEnabled(func: () -> Unit){
    if (AppConfig.ENABLE_LOG) func()
}
