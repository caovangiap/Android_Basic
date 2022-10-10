package com.example.android_basic.model


import android.os.Parcelable
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class Product(
    @SerializedName("Id_product")
    var Id_product: String?= null,
    @SerializedName("Name")
    var Name: String?= null,
    @SerializedName("Price")
    var Price: String?= null,
    @SerializedName("Condition")
    var Condition: String?=null,
    @SerializedName("Image")
    var Image: image = image(),
    @SerializedName("Size")
    var Size: String?=null
) : Parcelable {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "Name" to Name,
            "Price" to Price,
            "Size" to Size,
            "Condition" to Condition,
            "Image" to Image,
        )
    }
}

@Parcelize
data class image(
    @SerializedName("URL1")
    var URL1: URL1 = URL1(),
    @SerializedName("URL2")
    var URL2: URL1 = URL1()
) : Parcelable

@Parcelize
data class URL1(
    @SerializedName("IG1")
    var IG1: String?  = null,
    @SerializedName("IG2")
    var IG2: String? = null,
    @SerializedName("IG3")
    var IG3: String ? = null
) : Parcelable







