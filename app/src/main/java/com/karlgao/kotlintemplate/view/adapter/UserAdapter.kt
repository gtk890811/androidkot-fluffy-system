package com.karlgao.kotlintemplate.view.adapter

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.ListitemUserBinding
import com.karlgao.kotlintemplate.vm.UserVM

/**
 * Created by dev on 27/10/17.
 */


class UserAdapter(val items: MutableList<UserVM>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    lateinit private var onClick: () -> Unit

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val binding = holder.binding
        binding.user = items[position]

        binding.tvName.text = items[position].fullName.get()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        return UserViewHolder(DataBindingUtil.inflate(inflater, R.layout.listitem_user, parent, false))
    }

    inner class UserViewHolder(val binding: ListitemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClick.invoke()
            }
        }
    }

    fun onItemClick(event: () -> Unit) {
        onClick = event
    }
}