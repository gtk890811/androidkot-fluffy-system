package com.karlgao.kotlintemplate.view.util

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AlertDialog
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
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.alert
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

    fun <T> Observable<T>.init(): Observable<T> {
        return this.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun onErrorDefault(): (extra: String) -> Unit {
        return { extra ->
            ba.dismissPD()
            if (AppConfig.ENABLE_LOG) {
                //todo
//                AlertDialog.Builder(ba)
//                        .setTitle("Failure")
//                        .setMessage(e.throwable.message)
//                        .setPositiveButton("OK", null)
//                        .show()
//                ba.alert(R.string.error, extra)
            }
        }
    }

    fun onFailureDefault(): (extra: String) -> Unit {
        return { extra ->
            ba.dismissPD()
            if (AppConfig.ENABLE_LOG) {
                //todo
            }
        }
    }

    fun <T> Observable<T>.error(error: (extra: String) -> Unit = onErrorDefault(), failure: (extra: String) -> Unit): Observable<T> {
        return this.init()
                .doOnError { t ->
                    if (t is HttpException) {
                        val errorBody = t.response().errorBody()
                        val errorMessage = t.message()

                        var extra = ""
                        if (errorBody != null) {
                            val response: ResponseM<ErrorM> = ObjectMapper().readValue(errorBody.byteStream())
                            if (response.error != null) {
                                extra = response.error.messages.joinToString("\n")
                            }
                        }
                        if (extra.isEmpty()) extra = errorMessage
                        error.invoke(extra)
                    } else {
                        val extra = if (t.message != null) t.message!! else ""
                        failure.invoke(extra)
                    }
                }
    }
}