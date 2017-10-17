package com.karlgao.kotlintemplate.view.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.FragmentA2Binding
import com.karlgao.kotlintemplate.view.util.BaseSubFragment
import kotlinx.android.synthetic.main.fragment_a2.*

/**
 * Example fragment a2
 *
 * Created by Karl on 17/10/17.
 */

class A2Fragment: BaseSubFragment(){

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        DataBindingUtil.inflate<FragmentA2Binding>(inflater, R.layout.fragment_a2, container, false)
        return root
    }
}