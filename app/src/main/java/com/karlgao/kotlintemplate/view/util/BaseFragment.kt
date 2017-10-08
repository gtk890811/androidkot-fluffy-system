package com.karlgao.kotlintemplate.view.util

import android.support.v4.app.Fragment

/**
 * Created by dev on 12/9/17.
 */
open class BaseFragment : Fragment() {

    protected val base_activity: BaseActivity by lazy { activity as BaseActivity }

    
}