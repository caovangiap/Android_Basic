package com.example.android_basic.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_basic.model.Product
import com.example.android_basic.ui.allitems.AdapterProduct
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ViewModelNewProduct : ViewModel() {

    // chuyen man
    var nextAction = MutableLiveData<String>()

    // data của các product
    var dataNewProduct = mutableListOf<Product>()

    // instantce cuar fire store
    val db = Firebase.firestore


}