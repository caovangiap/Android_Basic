package com.example.android_basic.viewmodel

import android.content.ContentValues
import android.util.Log
import androidx.core.graphics.component1
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_basic.model.Product
import com.example.android_basic.model.order.AppDatabase
import com.example.android_basic.model.order.Bag
import com.example.android_basic.ui.allitems.AdapterProduct
import com.example.android_basic.ui.main.MainActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.FieldPosition
import kotlin.properties.Delegates

// hiển thị sản phẩm và thu thập thông tin đơn hàng
class ViewModelAllProduct : ViewModel() {
    // điều hướng các màn trong module liên quan đến sản phẩm
    var nextAction = MutableLiveData<String>()

    // component view thuoc detail items
    var buttonDetail1 = MutableLiveData<String>()
    var buttonDetail2 = MutableLiveData<String>()
    var nameItems = MutableLiveData<String>()
    var priceItems = MutableLiveData<String>()
    var imageProduct = MutableLiveData<String>()

    // data product items
    lateinit var dataItems: Product

    // size cua items
    var sizeItems = MutableLiveData<String>()

    // chuyển data từ vào data room và lưu thành đơn hàng
    var dataBag = AppDatabase.getDataBase(MainActivity.ApplicationContext).Bag()

    //  data item hien thi len detail
    fun dataItems(data: Product) {
        // image 2 buton
        buttonDetail1.postValue(data.Image.URL1.IG1!!)
        buttonDetail2.postValue(data.Image.URL2.IG1!!)
        // price and name items
        nameItems.postValue(data.Name!!)
        priceItems.postValue(data.Price!!)

        // DATA
        dataItems = data
        imageProduct.postValue(data.Image.URL1.IG1!!)
    }

    // button click 1
    fun click1() {
        imageProduct.postValue(dataItems.Image.URL1.IG1!!)
    }

    fun click2() {
        imageProduct.postValue(dataItems.Image.URL2.IG1!!)
    }

    // chọn size sản phẩm
    fun clickSize(size: String) {
        nextAction.postValue("DetailShoe")
        sizeItems.postValue(size)
    }

    // add data vào gio hang
    fun AddToBag() {
        nextAction.postValue("Bag")
        // chuyển data từ item vào data bag
        val Bag = dataItems.Name?.let {
            dataItems.Condition?.let { it1 ->
                dataItems.Price?.let { it2 ->
                    dataItems.Size?.let { it3 ->
                        dataItems.Image.URL1.IG1?.let { it4 ->
                            Bag(
                                null,
                                it, it1, it2, it3, it4, 1
                            )
                        }
                    }
                }
            }
        }
        if (Bag != null) {
            dataBag.insertAll(Bag)
        }
    }


}