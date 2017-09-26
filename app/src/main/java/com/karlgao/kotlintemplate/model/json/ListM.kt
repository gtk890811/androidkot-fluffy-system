package com.karlgao.kotlintemplate.model.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * List Model
 * Normally contains pagination object and data itself
 *
 * Created by Karl on 25/9/17.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ListM <out T> (
        val pagination: PaginationM?,
        val items: List<T>
)
