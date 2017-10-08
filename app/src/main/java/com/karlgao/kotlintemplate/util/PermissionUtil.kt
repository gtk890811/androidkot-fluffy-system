package com.karlgao.kotlintemplate.util

import android.Manifest

/**
 * Lists common permissions that will be used in app
 *
 * Created by Karl on 5/10/17.
 */

object PermissionUtil {
    val READ_CONTACTS = Manifest.permission.READ_CONTACTS
    val READ_EXTERNAL_STORAGE = Manifest.permission.READ_EXTERNAL_STORAGE
    val WRITE_EXTERNAL_STORAGE = Manifest.permission.WRITE_EXTERNAL_STORAGE
    val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
    val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
    val RECORD_AUDIO = Manifest.permission.RECORD_AUDIO
    val CALL_PHONE = Manifest.permission.CALL_PHONE
    val CAMERA = Manifest.permission.CAMERA
}
