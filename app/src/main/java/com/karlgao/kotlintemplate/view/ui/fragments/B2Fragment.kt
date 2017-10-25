package com.karlgao.kotlintemplate.view.ui.fragments

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.databinding.FragmentB2Binding
import com.karlgao.kotlintemplate.view.util.BaseSubFragment
import kotlinx.android.synthetic.main.fragment_b2.*

/**
 * Example fragment b2
 *
 * Created by Karl on 17/10/17.
 */

class B2Fragment : BaseSubFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = DataBindingUtil.inflate<FragmentB2Binding>(inflater, R.layout.fragment_b2, container, false).root

}