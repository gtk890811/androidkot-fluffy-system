package com.karlgao.kotlintemplate.view.adapter

import com.karlgao.kotlintemplate.R

/**
 * Created by dev on 27/10/17.
 */

class UserRI: BaseRecyclerItem(){

    override fun type(): Int {
        return R.layout.listitem_user
    }

}