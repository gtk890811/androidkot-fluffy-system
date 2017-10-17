package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivitySplashBinding
import com.karlgao.kotlintemplate.view.util.BaseActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity: BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)
        autoDismissKeyboard(root)

    }
}