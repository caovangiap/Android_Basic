package com.example.android_basic.viewmodel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_basic.model.History.DataItems
import com.example.android_basic.model.History.Information
import com.example.android_basic.model.Product
import com.example.android_basic.model.checkbuy.DecideBuy
import com.example.android_basic.model.order.Bag
import com.example.android_basic.ui.allitems.AdapterProduct
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ViewModelHistoryBuy : ViewModel() {

    // instantce cuar fire store
    val db = Firebase.firestore
    // id cua user
    val user = Firebase.auth.currentUser
    // thong tin nhan hang
    var dataInformation = mutableListOf<Information>()

    // list chi tiết mặt hàng
    var dataItems = mutableListOf<MutableList<DataItems>>()

    var nextAction = MutableLiveData<String>()

    // call data id don hang đe gọi ra chinh xac don hàng của user
    fun callData(){
        val id = mutableListOf<String>()

        // call id
        db.collection("Order")
            .whereEqualTo("id_user", user?.uid)
            .addSnapshotListener { snapshot, e ->

            if (e != null) {
                Log.w(ContentValues.TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

                // cac id don hang da dat
            for (doc in snapshot!!) {
                Log.d("test",doc.id)
                id.add(doc.id)
            }
                getDetailData(id)
        }

    }

    // call data chi tiet don hang
    fun getDetailData(id : MutableList<String>){
        for (i in 0 until id.size){
            db.collection("Order")
                .document(id[i])
                .collection("product")
                .whereEqualTo("condition", "true")
                .addSnapshotListener { snapshot, e ->

                    if (e != null) {
                        Log.w(TAG, "Listen failed.", e)
                        return@addSnapshotListener
                    }
                    // chi tiet mat hang
                    var detaiData = mutableListOf<DataItems>()
                    for (doc in snapshot!!) {
                        doc.toObject(DataItems::class.java).let { detaiData.add(it) }
                    }
                    // tat ca chi tiet dc add vao 1 list
                    dataItems.add(detaiData)
                    Log.d("data",dataItems.toString())
                }
        }
        // thong tin don hang
        db.collection("Order")
            .whereEqualTo("id_user", user?.uid)
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.w(ContentValues.TAG, "Listen failed.", error)
                    return@addSnapshotListener
                }
                // thong tin ve vi tri
                for (doc in value!!) {
                    Log.d("information",doc.toObject(DataItems::class.java).toString())
                    doc.toObject(Information::class.java).let { dataInformation.add(it) }
                }
            }
    }

    // reset data history để mảng data không bị chồng chéo data cũ
    fun resetDataHistory(){
        dataInformation.clear()
        dataItems.clear()
    }
}