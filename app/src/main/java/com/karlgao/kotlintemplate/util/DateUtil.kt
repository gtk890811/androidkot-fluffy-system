package com.karlgao.kotlintemplate.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


/**
 * Contains extension functions to transfer between date string and timestamp
 *
 * Created by Karl on 22/9/17.
 */

fun String.formattedTime_getTimestamp(): Long {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return try {
        sdf.parse(this).time
    } catch (e: ParseException) {
        e.printStackTrace()
        0
    }
}

fun String.displayTime_getTimestamp(): Long {
    val sdf = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())
    return try {
        sdf.parse(this).time
    } catch (e: ParseException) {
        e.printStackTrace()
        0
    }
}

fun Long.UTCTimestamp_getLocal(): Long {
    return this + TimeZone.getDefault().getOffset(this)
}

fun Long.localTimestamp_getUTC(): Long {
    return this - TimeZone.getDefault().getOffset(this)
}

fun Long.timestamp_getFormattedTime(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    return sdf.format(Date(this))
}

fun Long.timestamp_getDisplayTime(): String {
    val sdf = SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(Date(this))
}