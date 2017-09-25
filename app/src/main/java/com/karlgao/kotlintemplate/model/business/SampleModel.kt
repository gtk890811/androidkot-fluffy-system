package com.karlgao.kotlintemplate.model.business

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.realm.RealmObject
import io.realm.SampleRealmObjectWithParcelerAndJacksonRealmProxy
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey
import org.parceler.Parcel

/**
 * Models should be created as follow
 * Supports for Jackson, Realm, Parceler
 *
 * Created by Karl on 25/9/17.
 */


data class SampleDataClass(
        val normalField: String = "",
        val normalField2: String = ""
)

@Parcel(Parcel.Serialization.BEAN)
data class SampleDataClassWithParceler(
        val normalField: String = "",
        val normalField2: String = ""
)


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SampleDataClassWithJackson(
        val normalField: String = "",
        @JsonProperty("json") val jacksonAlternateNameField: String = "",
        @JsonIgnore val jacksonIgnoredField: String = ""
)


open class SampleRealmObject(
        var normalField: String = "",
        @Ignore var realmIgnoredField: String = "",
        @PrimaryKey var realmPrimaryKeyField: Int = 0
) : RealmObject()


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Parcel(implementations = arrayOf(SampleRealmObjectWithParcelerAndJacksonRealmProxy::class),
        value = Parcel.Serialization.BEAN,
        analyze = arrayOf(SampleRealmObjectWithParcelerAndJackson::class))
open class SampleRealmObjectWithParcelerAndJackson(
        var normalField: String = "",
        @JsonProperty("json") var jacksonAlternateNameField: String = "",
        @JsonIgnore var jacksonIgnoredField: String = "",
        @Ignore var realmIgnoredField: String = "",
        @PrimaryKey var realmPrimaryKeyField: Int = 0
) : RealmObject()