package com.karlgao.kotlintemplate.view.ui

import android.os.Bundle
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.view.util.BaseActivity

/**
 * Created by dev on 28/9/17.
 */


class ListActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}