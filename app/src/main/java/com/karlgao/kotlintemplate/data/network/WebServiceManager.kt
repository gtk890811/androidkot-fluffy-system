package com.karlgao.kotlintemplate.data.network

import com.karlgao.kotlintemplate.dagger.qualifier.BasicAuth
import com.karlgao.kotlintemplate.dagger.qualifier.TokenAuth
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Class that managers API call request body, tokenAuth will intercept the okhttp call by putting a token into the request header for authentication
 *
 * Created by Karl on 18/9/17.
 */

@Singleton
class WebServiceManager
@Inject constructor(
        @BasicAuth private val basicAuth: WebService,
        @TokenAuth private val tokenAuth: WebService) {


}