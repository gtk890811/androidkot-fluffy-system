package com.karlgao.kotlintemplate.model.json

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Response model
 *
 * Created by Karl on 25/9/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseM<out T>
@JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
        @JsonProperty("data") val data: T?,
        @JsonProperty("error") val error: ErrorM?
)
