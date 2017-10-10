package com.karlgao.kotlintemplate.view.util

import android.support.v4.app.Fragment

/**
 * Base Fragment
 *
 * Created by Karl on 10/10/17.
 */

open class BaseFragment : Fragment() {

    // create activity instance for easy access
    protected val base_activity: BaseActivity by lazy { activity as BaseActivity }


}