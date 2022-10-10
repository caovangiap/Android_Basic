package com.example.android_basic.viewmodel

import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_basic.R
import com.example.android_basic.ui.login.FragmentLogin
import com.example.ttcs_shoppinng_mall.data.model.NewFashion
import com.example.ttcs_shoppinng_mall.ui.adapter.fragmentmain.AdapterFragment_newFashion
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import org.w3c.dom.Comment

// hiển thị trang chủ của app

class ViewModelManager  : ViewModel() {
    var nextAction = MutableLiveData<String>()
    private val database_NewFashion= Firebase.database.reference
    // data cac bai bao
    var LiveDataNewFashion = MutableLiveData<MutableList<NewFashion>>()

    // các sự kiện nút bấm
    fun actionAllProduct(){
        // hien thi tat ca san pham
        nextAction.postValue("ALL_PRODUCT")
    }
    // chuyen sang lich su mua hang
    fun history(){
        // hien thị lịch sử mua hang
        nextAction.postValue("HistoryBuy")
    }
    // chuyen sang new product
    fun newProduct(){
        nextAction.postValue("NewProduct")
    }
    // chuyen sang man profile
    fun profile(){
        nextAction.postValue("profile")
    }

    
    // adapter phần tin tức thời trang ( this wwek's hightlight)
    fun recycler_NewFashion(context: Activity) {

        val PathDataNew = database_NewFashion.child("New_Fashion")
        val DataNewFashion = mutableListOf<NewFashion>()

        val childEventListener = object : ChildEventListener {
            // lấy data về
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {

                if(dataSnapshot.exists()){
                    val data_new = dataSnapshot.getValue<NewFashion>()
                    Log.d(ContentValues.TAG,data_new.toString())
                    if (data_new!=null){
                        DataNewFashion.add(data_new)
                        // data cac bai bao ddc hien thi
                        LiveDataNewFashion.postValue(DataNewFashion)
                    }
                }
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(ContentValues.TAG, "onChildChanged: ${dataSnapshot.key}")

                val data_new = dataSnapshot.getValue<NewFashion>()
                if (data_new != null) {
                    DataNewFashion.add(data_new)
                    LiveDataNewFashion.postValue(DataNewFashion)
                }
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.d(ContentValues.TAG, "onChildRemoved:" + dataSnapshot.key!!)
                val commentKey = dataSnapshot.key

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, previousChildName: String?) {
                Log.d(ContentValues.TAG, "onChildMoved:" + dataSnapshot.key!!)
                val movedComment = dataSnapshot.getValue<Comment>()
                val commentKey = dataSnapshot.key
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(ContentValues.TAG, "postComments:onCancelled", databaseError.toException())
                Toast.makeText(context, "Failed to load comments.",
                    Toast.LENGTH_SHORT).show()
            }
        }
        PathDataNew.addChildEventListener(childEventListener)

    }


}