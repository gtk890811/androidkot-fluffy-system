package com.karlgao.kotlintemplate.util

import com.karlgao.kotlintemplate.AppConfig
import java.util.regex.Pattern

/**
 * Contains extension function on String
 *
 * Created by Karl on 22/9/17.
 */

fun String.email_isValid(): Boolean {
    val expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
    val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.password_matchLength(): Boolean {
    return length >= AppConfig.PASSWORD_LENGTH
}

fun String.password_hasUpperCase(): Boolean {
    return this != toLowerCase()
}

fun String.password_hasLowerCase(): Boolean {
    return this != toUpperCase()
}

fun String.password_hasNumber(): Boolean {
    return this.matches(Regex(".*\\d+.*"))
}

fun String.password_hasNoneAlphaNumeric(): Boolean {
    return !this.matches(Regex("[A-Za-z0-9 ]*"))
}