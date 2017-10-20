package com.karlgao.kotlintemplate.view.ui

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ActivitySigninBinding
import com.karlgao.kotlintemplate.util.onTextChange
import com.karlgao.kotlintemplate.view.util.BaseActivity
import com.karlgao.kotlintemplate.vm.UserVM
import kotlinx.android.synthetic.main.activity_signin.*
import java.util.*


/**
 * Created by dev on 28/9/17.
 */


class SigninActivity : BaseActivity() {

    val vm: UserVM by lazy { UserVM() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivitySigninBinding>(this, R.layout.activity_signin)
        binding.user = vm

        autoDismissKeyboard(root)

        initAction()
    }

    private fun initAction() {

        et_email.onTextChange { til_email.error = null }

        et_password.onTextChange { til_password.error = null }

        btn_login.setOnClickListener {
            if(validateInput()) {
                vm.login()
            }
        }
    }

    private fun validateInput(): Boolean {
        var pass = true
        til_email.error = null
        til_password.error = null

        if(!vm.validateEmail()){
            pass = false
            til_email.error = resources.getString(R.string.invalid_field)
            YoYo.with(Techniques.Shake).duration(500).playOn(et_email)
        }

        if(!vm.validatePassword()){
            pass = false
            til_password.error = resources.getString(R.string.invalid_field)
            YoYo.with(Techniques.Shake).duration(500).playOn(et_password)
        }

        return pass
    }

}