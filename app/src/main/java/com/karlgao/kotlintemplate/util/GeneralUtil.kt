package com.karlgao.kotlintemplate.util

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.karlgao.kotlintemplate.view.util.EndlessScrollListener

/**
 * Contains general Util Method
 *
 * Created by Karl on 25/9/17.
 */

fun EditText.onTextChange(func: () -> Unit) {

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

fun RecyclerView.setOnLoadMoreListener(func: () -> Unit) {
    this.addOnScrollListener(object : EndlessScrollListener() {
        override fun onLoadMore() {
            func.invoke()
        }
    })
}


