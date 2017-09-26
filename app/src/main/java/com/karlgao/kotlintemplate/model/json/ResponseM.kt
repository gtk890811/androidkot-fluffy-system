package com.karlgao.kotlintemplate.model.json

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Response model
 *
 * Created by Karl on 25/9/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseM<out T> (
    val data: T?,
    val error: ErrorM?
)
