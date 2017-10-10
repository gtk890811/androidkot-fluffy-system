package com.karlgao.kotlintemplate.vm

import com.karlgao.kotlintemplate.data.DataManager
import com.karlgao.kotlintemplate.vm.util.BaseVM
import javax.inject.Inject

/**
 * View Model to handle the user list
 *
 * Created by Karl on 10/10/17.
 */

class UserListVM: BaseVM() {

    @Inject
    lateinit var dm: DataManager

    init {
        vmComponent.inject(this)
    }

    val vms: List<UserVM> = ArrayList()

    fun getUsers(){
        
    }
}