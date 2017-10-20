package com.karlgao.kotlintemplate.vm

import android.databinding.ObservableField
import com.karlgao.kotlintemplate.data.DataManager
import com.karlgao.kotlintemplate.model.business.AccessTokenM
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.model.json.ResponseM
import com.karlgao.kotlintemplate.util.email_isValid
import com.karlgao.kotlintemplate.util.password_matchLength
import com.karlgao.kotlintemplate.vm.util.BaseVM
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

/**
 * View Model for User
 *
 * Created by Karl on 10/10/17.
 */

class UserVM(var model: UserM = UserM()) : BaseVM() {

    @Inject
    lateinit var dm: DataManager

    val firstName: ObservableField<String> = ObservableField()
    val lastName: ObservableField<String> = ObservableField()
    val email: ObservableField<String> = ObservableField()
    val password: ObservableField<String> = ObservableField()

    init {
        vmComponent.inject(this)
        initVM()
    }

    private fun initVM() {
        firstName.set(model.first_name)
        lastName.set(model.last_name)
        email.set(model.email)
        password.set("")
    }

    private fun syncModel(): UserM {
        return model
    }

    fun validateEmail(): Boolean {
        return !email.get().isEmpty() && email.get().email_isValid()
    }

    fun validatePassword(): Boolean {
        return !password.get().isEmpty() && password.get().password_matchLength()
    }

    private fun randomPass(): Boolean {
        return Random().nextInt(100) <= 49
    }

    fun login(): Observable<ResponseM<AccessTokenM>> {
        return if (randomPass()) dm.web.signIn(email.get(), password.get()) else dm.web.signInFail(email.get(), password.get())
                .doOnNext { user ->
                    dm.prefs.accessToken = user.data?.token
                }
    }

}
