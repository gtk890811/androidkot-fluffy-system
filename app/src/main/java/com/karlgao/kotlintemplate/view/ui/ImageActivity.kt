package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivityImageBinding
import com.karlgao.kotlintemplate.view.util.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

/**
 * Camera and gallery sample
 *
 * Created by Karl on 23/10/17.
 */

class ImageActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DataBindingUtil.setContentView<ActivityImageBinding>( this, R.layout.activity_image)

        setSupportActionBar(toolbar)
    }
}