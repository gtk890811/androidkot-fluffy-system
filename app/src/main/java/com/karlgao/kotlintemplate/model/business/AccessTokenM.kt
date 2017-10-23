package com.karlgao.kotlintemplate.model.business

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * Contains access token and a user object
 *
 * Created by Karl on 25/9/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class AccessTokenM (
        @JsonProperty("access_token") val token : String = "",
        @JsonProperty("user") val user : UserM = UserM()
)