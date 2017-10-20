package com.karlgao.kotlintemplate.data.preference

import org.jraf.android.prefs.Prefs
import org.jraf.android.prefs.DefaultString

/**
 * Definition for each shared preference entry
 *
 * Created by Karl on 18/9/17.
 */
@Prefs
class Main {

    @DefaultString("")
    lateinit var accessToken: String

    @DefaultString("")
    lateinit var deviceToken: String
}