package com.karlgao.kotlintemplate.data.preference

import org.jraf.android.prefs.Prefs
import org.jraf.android.prefs.DefaultString

/**
 * Created by dev on 12/9/17.
 */
@Prefs
class PrefsKeys {

    @DefaultString("")
    var accessToken: String? = null

}