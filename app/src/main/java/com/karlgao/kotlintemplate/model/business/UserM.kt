package com.karlgao.kotlintemplate.model.business

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * User Model
 *
 * Created by Karl on 25/9/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserM (
        val id : Int = -1,
        val first_name: String = "",
        val last_name:String = "",
        val email: String = "",
        val settings : SettingM = SettingM()
)
