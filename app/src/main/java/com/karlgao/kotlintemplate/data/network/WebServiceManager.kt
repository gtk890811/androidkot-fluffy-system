package com.karlgao.kotlintemplate.data.network

import com.fasterxml.jackson.databind.ObjectMapper
import com.karlgao.kotlintemplate.dagger.qualifier.BasicAuth
import com.karlgao.kotlintemplate.dagger.qualifier.TokenAuth
import com.karlgao.kotlintemplate.model.business.AccessTokenM
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.model.json.ListM
import com.karlgao.kotlintemplate.model.json.ResponseM
import io.reactivex.Observable
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

    private val mapper: ObjectMapper by lazy { ObjectMapper() }

    fun signIn(email: String, password: String): Observable<ResponseM<AccessTokenM>> {
        val j = mapper.createObjectNode()

        j.put("email", email)
        j.put("password", password)

        return basicAuth.sigIn(j)
    }

    fun signInFail(email: String, password: String): Observable<ResponseM<AccessTokenM>> {
        val j = mapper.createObjectNode()

        j.put("email", email)
        j.put("password", password)

        return basicAuth.signInFail(j)
    }

    fun getList(): Observable<ResponseM<ListM<UserM>>> {
        return tokenAuth.getList(HashMap())
    }
}