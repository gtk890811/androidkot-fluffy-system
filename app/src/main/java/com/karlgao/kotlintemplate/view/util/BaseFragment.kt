package com.karlgao.kotlintemplate.view.util

import android.support.v4.app.Fragment
import com.karlgao.kotlintemplate.view.ui.MainActivity

/**
 * Base Fragment
 *
 * Created by Karl on 10/10/17.
 */

open class BaseFragment : Fragment(),ContextInterface {

    override val ba: BaseActivity
        get() = activity as BaseActivity

    // create activity instance for easy access
    protected val baseActivity: BaseActivity by lazy { activity as BaseActivity }

    protected val mainActivity: MainActivity by lazy { activity as MainActivity }

}