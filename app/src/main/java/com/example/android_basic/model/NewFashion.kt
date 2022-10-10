package com.example.ttcs_shoppinng_mall.data.model


import android.os.Parcelable
import com.google.firebase.database.Exclude

import com.google.firebase.database.PropertyName
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName


@Parcelize
data class NewFashion(
    @SerializedName("Image")
    var Image: String? = null,
    @SerializedName("Title")
    @get: PropertyName("Title") @set: PropertyName("Title")
    var Titel: String? = null,
    @SerializedName("URL")
    @get: PropertyName("URL") @set: PropertyName("URL")
    var URL: String? = null,

    ) : Parcelable {



    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "Image" to Image,
            "Titel" to Titel,
            "URL" to URL
        )
    }
}
