package com.karlgao.kotlintemplate.vm

import com.karlgao.kotlintemplate.data.DataManager
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.model.json.ListM
import com.karlgao.kotlintemplate.model.json.QueryM
import com.karlgao.kotlintemplate.model.json.ResponseM
import com.karlgao.kotlintemplate.vm.util.BaseVM
import io.reactivex.Single
import java.util.*
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

    private var nextQuery : QueryM = QueryM()

    init {
        vmComponent.inject(this)
    }

    fun getUsers(isLoadMore: Boolean): Single<ResponseM<ListM<UserM>>> {
        return dm.web.getList(nextQuery.getHashMap())
                .doOnSuccess { response ->
                    val models = response.data?.items ?: return@doOnSuccess
                    if (isLoadMore) {
                        vms.plusAssign(models.map { UserVM(it) })
                    } else {
                        vms.clear()
                        models.mapTo(vms) { UserVM(it) }
                    }

                    val pagination = response.data.pagination ?: return@doOnSuccess
                    if (pagination.current_page < pagination.last_page){
                        nextQuery.page = pagination.current_page + 1
                        nextQuery.per_page = pagination.per_page
                    } else {
                        nextQuery.page = -1
                    }
                }
    }

    fun isLastPage(): Boolean{
        return nextQuery.page == -1
    }

    fun addNewVM(position: Int){
        val vm = UserVM()
        val dice = Random().nextInt(4)
        when (dice){
            0 -> vm.setFullName("I'm new ! ")
            1 -> vm.setFullName("Hello world !")
            2 -> vm.setFullName("Yo man, what's up ! ")
            3 -> vm.setFullName("It's so cooooooool ! ")
        }
        vms.add(position, vm)
    }

    fun removeVM(position: Int){
        vms.removeAt(position)
    }
}