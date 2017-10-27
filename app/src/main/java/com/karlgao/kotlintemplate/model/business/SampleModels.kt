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
 * tips:
 * * whether using val or var is based on whether the model will be used for later change
 * * if one field is annotated with JsonProperty, all field need that, see AccessTokenM
 * * classes with field that does not have default value need the JsonCreator Annotation, see ResponseM, ListM
 * * when use JsonCreator, all field needs to be annotated with JsonProperty
 *
 *
 * Created by Karl on 25/9/17.
 */

// Basic type
data class SampleDataClass(
        var normalField: String = "",
        val normalField2: String = ""
)


// Most commonly used as most model is built based on APIs
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class SampleDataClassWithJackson(
        val normalField: String = "",
        @JsonProperty("json") val jacksonAlternateNameField: String = "",
        @JsonIgnore val jacksonIgnoredField: String = ""
)


// In case we need to pass model/vm between views
// We can use data manager to replace parceler most of the time
@Parcel(Parcel.Serialization.BEAN)
data class SampleDataClassWithParceler(
        val normalField: String = "",
        val normalField2: String = ""
)


// Objects that need to store in Realm database does not support data class yet
open class SampleRealmObject(
        var normalField: String = "",
        @Ignore var realmIgnoredField: String = "",
        @PrimaryKey var realmPrimaryKeyField: Int = 0
) : RealmObject()


// Everything together
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