package com.karlgao.kotlintemplate.data.network

import com.karlgao.kotlintemplate.dagger.qualifier.BasicAuth
import com.karlgao.kotlintemplate.dagger.qualifier.TokenAuth
import com.karlgao.kotlintemplate.model.business.AccessTokenM
import com.karlgao.kotlintemplate.model.business.UserM
import com.karlgao.kotlintemplate.model.json.ListM
import com.karlgao.kotlintemplate.model.json.ResponseM
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.android.schedulers.AndroidSchedulers
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

    fun <T> Observable<T>.init(): Observable<T> {
        return this.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

    fun sign_in(): Observable<ResponseM<AccessTokenM>>{
        return basicAuth.sign_in().init()
    }

    fun sign_in_fail(): Observable<ResponseM<AccessTokenM>>{
        return basicAuth.sign_in_fail().init()
    }

    fun get_list(): Observable<ResponseM<ListM<UserM>>>{
        return tokenAuth.get_list().init()
    }
}