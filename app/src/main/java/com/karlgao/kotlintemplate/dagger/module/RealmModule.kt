package com.karlgao.kotlintemplate.dagger.module

import dagger.Module
import io.realm.Realm
import javax.inject.Singleton
import dagger.Provides

/**
 * Module that provides realm object
 *
 * Created by Karl on 15/9/17.
 */

@Module
class RealmModule {
    @Provides
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }
}