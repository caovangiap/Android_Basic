package com.example.android_basic.model.product

import android.os.Parcelable
import com.example.android_basic.model.image
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class AccessoryData(
    @SerializedName("Id_product")
    var Id_product: String? = null,
    @SerializedName("Name")
    var Name: String? = null,
    @SerializedName("Price")
    var Price: String? = null,
    @SerializedName("Condition")
    var Condition: String? = null,
    @SerializedName("Image")
    var Image: String? = null,
    @SerializedName("Size")
    var Size: String? = null
) : Parcelable
