package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivitySplashBinding
import com.karlgao.kotlintemplate.view.util.BaseActivity
import org.jetbrains.anko.startActivity

/**
 * Sample splash screen
 *
 * Created by Karl on 23/10/17.
 */

class SplashActivity : BaseActivity() {

    companion object {
        const val SPLASH_DELAY: Int = 3000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fullScreen()
        DataBindingUtil.setContentView<ActivitySplashBinding>(this, R.layout.activity_splash)

        initAction()
    }

    private fun initAction() {
        Handler().postDelayed({
            startActivity<SigninActivity>()
        }, SPLASH_DELAY.toLong())
    }
}