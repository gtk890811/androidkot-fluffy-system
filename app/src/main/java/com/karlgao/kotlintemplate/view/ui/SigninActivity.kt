package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.karlgao.kotlintemplate.AppConfig
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivitySigninBinding
import com.karlgao.kotlintemplate.util.success
import com.karlgao.kotlintemplate.util.onTextChange
import com.karlgao.kotlintemplate.view.util.BaseActivity
import com.karlgao.kotlintemplate.vm.UserVM
import kotlinx.android.synthetic.main.activity_signin.*
import org.jetbrains.anko.startActivity


/**
 * Signin sample
 *
 * Created by Karl on 23/10/17.
 */

class SigninActivity : BaseActivity() {

    private val vm: UserVM by lazy { UserVM() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySigninBinding>(this, R.layout.activity_signin)
        binding.user = vm

        autoDismissKeyboard(ll_root)

        initView()
        initAction()
    }

    private fun initView() {
        val debugEmail = "karl@yopmail.com"
        val debugPassword = "123456"

        AppConfig.isLogEnabled {
            vm.email.set(debugEmail)
            vm.password.set(debugPassword)
        }
    }

    private fun initAction() {

        et_email.onTextChange { til_email.error = null }

        et_password.onTextChange { til_password.error = null }

        btn_login.setOnClickListener {
            if (validateInput()) {
                showPD()
                vm.login().init()
                        .success {
                            dismissPD()
                            startActivity<MainActivity>()
                        }
            }
        }
    }

    private fun validateInput(): Boolean {
        var pass = true
        til_email.error = null
        til_password.error = null

        if (!vm.validateEmail()) {
            pass = false
            til_email.error = getString(R.string.invalid_field)
            YoYo.with(Techniques.Shake).duration(500).playOn(et_email)
        }

        if (!vm.validatePassword()) {
            pass = false
            til_password.error = getString(R.string.invalid_field)
            YoYo.with(Techniques.Shake).duration(500).playOn(et_password)
        }

        return pass
    }
}