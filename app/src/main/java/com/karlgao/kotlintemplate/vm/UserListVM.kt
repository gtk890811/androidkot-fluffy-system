package com.karlgao.kotlintemplate.vm

import com.karlgao.kotlintemplate.data.DataManager
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.model.json.ListM
import com.karlgao.kotlintemplate.model.json.ResponseM
import com.karlgao.kotlintemplate.vm.util.BaseVM
import io.reactivex.Single
import javax.inject.Inject

/**
 * View Model to handle the user list
 *
 * Created by Karl on 10/10/17.
 */

class UserListVM : BaseVM() {

    @Inject
    lateinit var dm: DataManager

    val vms: MutableList<UserVM> = arrayListOf()

    init {
        vmComponent.inject(this)
    }

    fun getUsers(): Single<ResponseM<ListM<UserM>>> {
        return dm.web.getList()
                .doOnSuccess { response ->
                    val models = response.data?.items ?: return@doOnSuccess
                    vms.clear()
                    models.mapTo(vms) { UserVM(it) }
                }
    }
}