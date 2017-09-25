package com.karlgao.kotlintemplate.model.json

/**
 * Error model
 *
 * Created by Karl on 25/9/17.
 */

data class mError(
        val statusCode: Int = -1,
        val error: String = "",
        val message: String = ""
)