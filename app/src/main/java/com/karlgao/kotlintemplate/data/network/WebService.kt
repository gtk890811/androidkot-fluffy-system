package com.karlgao.kotlintemplate.data.network

import com.karlgao.kotlintemplate.model.business.AccessTokenM
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.model.json.ListM
import com.karlgao.kotlintemplate.model.json.ResponseM
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Define the endpoint for each API
 *
 * Created by Karl on 18/9/17.
 */

interface WebService {

    @POST("signin")
    fun sign_in(): Observable<ResponseM<AccessTokenM>>

    @POST("signin_fail")
    fun sign_in_fail(): Observable<ResponseM<AccessTokenM>>

    @GET("list")
    fun get_list(): Observable<ResponseM<ListM<UserM>>>
}