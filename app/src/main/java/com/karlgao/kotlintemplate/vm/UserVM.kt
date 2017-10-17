package com.karlgao.kotlintemplate.vm

import android.databinding.ObservableField
import com.karlgao.kotlintemplate.data.DataManager
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.vm.util.BaseVM
import javax.inject.Inject

/**
 * View Model for User
 *
 * Created by Karl on 10/10/17.
 */

class UserVM(var model: UserM = UserM()) : BaseVM() {

    @Inject
    lateinit var dm: DataManager

    val first_name: ObservableField<String> = ObservableField()
    val last_name: ObservableField<String> = ObservableField()
    val email: ObservableField<String> = ObservableField()
    val password: ObservableField<String> = ObservableField()

    init {
        vmComponent.inject(this)
        initVM()
    }

    private fun initVM(){
        first_name.set(model.first_name)
        last_name.set(model.last_name)
        email.set(model.email)
        password.set("")
    }

    private fun syncModel(): UserM {
        return model
    }

    fun login() {

    }
}
