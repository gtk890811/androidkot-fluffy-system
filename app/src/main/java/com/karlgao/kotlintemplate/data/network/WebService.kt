package com.karlgao.kotlintemplate.data.network

import com.fasterxml.jackson.databind.node.ObjectNode
import com.karlgao.kotlintemplate.model.business.AccessTokenM
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.model.json.ListM
import com.karlgao.kotlintemplate.model.json.ResponseM
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryMap

/**
 * Define the endpoint for each API
 *
 * Created by Karl on 18/9/17.
 */

interface WebService {

    @POST("signin")
    fun sigIn(@Body user: ObjectNode): Observable<ResponseM<AccessTokenM>>

    @POST("signin_fail")
    fun signInFail(@Body user: ObjectNode): Observable<ResponseM<AccessTokenM>>

    @GET("list")
    fun getList(@QueryMap query: HashMap<String, String>): Observable<ResponseM<ListM<UserM>>>
}