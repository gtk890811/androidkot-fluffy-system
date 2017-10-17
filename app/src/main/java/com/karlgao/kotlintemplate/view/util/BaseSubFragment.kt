package com.karlgao.kotlintemplate.view.util

import android.view.animation.Animation
import com.karlgao.kotlintemplate.view.ui.MainActivity
import android.view.animation.AlphaAnimation



/**
 * Created by dev on 17/10/17.
 */

open class BaseSubFragment : BaseFragment() {

    protected val containerFragment: ContainerFragment by lazy { parentFragment as ContainerFragment }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation {
        // Apply the workaround only if this is a child fragment, and the parent
        // is being removed || detached.
//        if(!enter && parent != null && !parent.isVisible())){
        if (!enter && parentFragment != null && (parentFragment.isRemoving || parentFragment.isDetached)) {
            val doNothingAnim = AlphaAnimation(1f, 1f)
            //duration is based on the animation fade out duration in R.anim.fade_out
            doNothingAnim.duration = 300
            return doNothingAnim
        }
        return super.onCreateAnimation(transit, enter, nextAnim)
    }
}