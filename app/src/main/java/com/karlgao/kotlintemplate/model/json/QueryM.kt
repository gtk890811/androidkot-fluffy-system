package com.karlgao.kotlintemplate.model.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.karlgao.kotlintemplate.AppConfig

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
        var per_page: Int = AppConfig.DEFAULT_PER_PAGE,
        var page: Int = 0
) {
    fun getHashMap(): HashMap<String, String> {
        return hashMapOf(
                Pair("page", page.toString()),
                Pair("per_page", per_page.toString())
        )
    }
}
