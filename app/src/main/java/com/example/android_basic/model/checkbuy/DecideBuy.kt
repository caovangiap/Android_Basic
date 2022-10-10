package com.example.android_basic.model.checkbuy

import android.os.Parcelable
import com.example.android_basic.model.order.Bag
import com.google.firebase.Timestamp


import kotlinx.parcelize.Parcelize
import java.util.*
import kotlin.collections.ArrayList

@Parcelize
data class DecideBuy(

    val name: String? = null,
    val address: String? = null,
    val note: String? = null,
    val phoneNumber: String? = null,
    val timestamp: String? = null,
    val id_user : String?= null,
    val total : String? = null,
    val complate : String
) : Parcelable
