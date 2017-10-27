package com.karlgao.kotlintemplate.model.json

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * List Model
 * Normally contains pagination object and data itself
 *
 * Created by Karl on 25/9/17.
 */


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class ListM <out T>
@JsonCreator(mode = JsonCreator.Mode.DELEGATING) constructor(
        @JsonProperty("pagination") val pagination: PaginationM?,
        @JsonProperty("items") val items: List<T>
)
