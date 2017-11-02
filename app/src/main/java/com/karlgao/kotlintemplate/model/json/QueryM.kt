package com.karlgao.kotlintemplate.model.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Query Model
 *
 * Handles complex url parameter used in the app when hitting Api endpoint
 * e.g. when hitting an endless scroll list, it is likely that at least two parameter needs to be sent(page, per_page)
 *
 * Created by Karl on 25/9/17.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class QueryM(
        val per_page: Int = 0,
        val page: Int = 0
) {
    fun getHashMap(): HashMap<String, String> {
        return hashMapOf(
                Pair("page", page.toString()),
                Pair("per_page", per_page.toString())
        )
    }
}
