package com.karlgao.kotlintemplate

import timber.log.Timber

/**
 * Created by dev on 19/9/17.
 */
class Test(var int: Int = 2) {



    private var str: String = "asd"
        get() = field
        set(value) {
            field = "asdd"
        }

    init {
        Timber.d("init" + int)
    }


    constructor(str: String) : this(1) {
        this.str = str
        Timber.d("bababa" + int)
    }
}