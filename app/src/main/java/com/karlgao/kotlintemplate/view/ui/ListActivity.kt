package com.karlgao.kotlintemplate.view.ui

import android.os.Bundle
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.view.util.BaseActivity

/**
 * Recycler view sample
 *
 * Created by Karl on 23/10/17.
 */

class ListActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}