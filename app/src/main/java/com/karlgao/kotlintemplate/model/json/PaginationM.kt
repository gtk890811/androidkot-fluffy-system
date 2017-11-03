package com.karlgao.kotlintemplate.model.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Pagination Model
 *
 * Created by Karl on 25/9/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class PaginationM (
        val total: Int = 0,
        val per_page : Int = 0,
        val current_page: Int = 0,
        val last_page: Int = 0,
        val next_page_url: String = "",
        val prev_page_url: String = "",
        val from: Int = 0,
        val to: Int = 0
)
