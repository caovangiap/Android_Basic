package com.example.android_basic.model.order

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Bag(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long?,
    @ColumnInfo(name = "Name")
    @SerializedName("name_Product")
    val name_Product: String,

    @ColumnInfo(name = "Condition")
    @SerializedName("condition")
    val condition: String,

    @ColumnInfo(name = "Price")
    @SerializedName("price")
    val price: String,
    @ColumnInfo(name = "Size")
    @SerializedName("size")
    val size: String,

    @ColumnInfo(name = "Image")
    @SerializedName("image")
    val image: String,

    @ColumnInfo(name = "Number")
    @SerializedName("number")
    val number : Int
) : Parcelable

