package com.karlgao.kotlintemplate.model.business

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude

/**
 * Setting Model
 *
 * Created by Karl on 25/9/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SettingM(
        val push_notifications: Boolean = false,
        val email_notifications: Boolean = false
)