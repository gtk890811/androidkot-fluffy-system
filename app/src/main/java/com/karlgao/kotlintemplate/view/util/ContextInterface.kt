package com.karlgao.kotlintemplate.view.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.karlgao.kotlintemplate.AppConfig
import com.karlgao.kotlintemplate.R
import com.karlgao.kotlintemplate.model.json.ErrorM
import com.karlgao.kotlintemplate.model.json.ResponseM
import com.karlgao.kotlintemplate.util.rxBuild
import io.reactivex.Observable
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import retrofit2.HttpException


/**
 * Holds the general method that will be used in Activity, Fragment... especially those methods that need context as a input
 * Base Activity and Base Fragment implements this interface. Holds a reference to the baseActivity as context
 *
 * Created by Karl on 20/10/17.
 */

interface ContextInterface {

    val ba: BaseActivity

    fun hideSoftKeyboard() {
        val inputMethodManager = ba.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (ba.currentFocus != null) {
            inputMethodManager.hideSoftInputFromWindow(ba.currentFocus.windowToken, 0)
            ba.currentFocus.clearFocus()
        }
    }

    fun autoDismissKeyboard(view: View) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (view !is EditText) {
            view.setOnTouchListener { _, _ ->
                hideSoftKeyboard()
                false
            }
        }

        //If a layout container, iterate over children and seed recursion.
        if (view is ViewGroup) {
            (0 until view.childCount)
                    .map { view.getChildAt(it) }
                    .forEach { autoDismissKeyboard(it) }
        }
    }

    fun hasInternetConnection(): Boolean {
        val connectivityManager = ba.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }

    fun isTablet(func: () -> Unit) {
        if (ba.resources.getBoolean(R.bool.is_tablet)) {
            func()
        }
    }

    fun Int.dpToPx(): Int {
        val density = ba.resources.displayMetrics.density
        return (this * density).toInt()
    }

    fun Int.pxToDp(): Int {
        val density = ba.resources.displayMetrics.density
        return (this / density).toInt()
    }

    fun getColorRes(@ColorRes res: Int): Int {
        return ContextCompat.getColor(ba, res)
    }

    fun onErrorDefault(): (extra: String) -> Unit {
        return { extra ->
            ba.dismissPD()
            ba.alert(extra, ba.getString(R.string.error)) {
                okButton {}
            }.show()
        }
    }

    fun onFailureDefault(): (extra: String) -> Unit {
        return { extra ->
            ba.dismissPD()
            ba.alert(extra, ba.getString(R.string.failure)) {
                okButton {}
            }.show()
        }
    }

    // init network calls with general error handler, or pass in customise error handler
    fun <T> Observable<T>.init(error: (extra: String) -> Unit = onErrorDefault(), failure: (extra: String) -> Unit = onFailureDefault()): Observable<T> {
        return this.rxBuild()
                .doOnError { t ->
                    if (t is HttpException) {
                        val errorBody = t.response().errorBody()
                        val errorMessage = t.message()
                        val errorCode = t.code()

                        var msg = ""
                        if (errorBody != null) {
                            val response: ResponseM<ErrorM> = ObjectMapper().readValue(errorBody.byteStream())
                            msg = response.error?.messages?.joinToString("\n") ?: ""
                        }
                        if (msg.isEmpty()) {
                            msg = ba.getString(R.string.network_error_message_prod)
                            AppConfig.isLogEnabled { msg = errorCode.toString() + " " + errorMessage }
                        }
                        error.invoke(msg)
                    } else {
                        var msg = ba.getString(R.string.network_failure_message_prod)
                        AppConfig.isLogEnabled {
                            msg = if (t.message != null) t.message else ba.getString(R.string.network_failure_message)
                        }
                        failure.invoke(msg)
                    }
                }
    }
}