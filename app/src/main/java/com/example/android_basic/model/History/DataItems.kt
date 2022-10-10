package com.example.android_basic.model.History

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@IgnoreExtraProperties
data class DataItems(

    @SerializedName("id")
    val id : Long? = 0,
    @SerializedName("name_Product")
    val name_Product: String? = "",
    @SerializedName("condition")
    val condition: String? = "",
    @SerializedName("price")
    val price: String? ="",
    @SerializedName("size")
    val size: String? = "",
    @SerializedName("image")
    val image: String? = "",
    @SerializedName("number")
    val number: Int ? = 0
) : Parcelable{

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "name_Product" to name_Product,
            "condition" to condition,
            "size" to size,
            "id" to id,
            "Image" to image,
            "number" to number,
            "price" to price,
        )
    }
}
