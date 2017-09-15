package com.karlgao.kotlintemplate.dagger.module

import dagger.Module
import io.realm.Realm
import javax.inject.Singleton
import dagger.Provides





/**
 * Created by dev on 12/9/17.
 */

@Module
class RealmModule {
    @Provides
    @Singleton
    fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }
}