package com.karlgao.kotlintemplate.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * Contains general Util Method
 *
 * Created by Karl on 25/9/17.
 */

inline fun EditText.onTextChange(crossinline func: () -> Unit) {

    this.addTextChangedListener(object : TextWatcher {

        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            func.invoke()
        }
    })
}


